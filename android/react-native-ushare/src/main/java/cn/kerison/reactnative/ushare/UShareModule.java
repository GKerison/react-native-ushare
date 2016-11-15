package cn.kerison.reactnative.ushare;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import cn.kerison.ushare.UShareHelper;

/**
 * Created by k on 2016/11/7.
 */

public class UShareModule extends ReactContextBaseJavaModule {

    public static final String MODULE_NAME = "RCTUShare";

    public static final String SHARE_FAILED = "-1";
    public static final String SHARE_CANCEL = "0";
    public static final String SHARE_SUCCESS = "1";

    public UShareModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void share(final String shareType, final String title, final String desc, String image, final String url, final Callback callback) {
        final SHARE_MEDIA platform = SHARE_MEDIA.convertToEmun(shareType);
        if (platform == null) {
            Log.w(MODULE_NAME, "no share type for :" + shareType);
            if (callback != null) {
                callback.invoke(SHARE_CANCEL);
            }
            return;
        }

        final UMImage shareImage = new UMImage(getCurrentActivity(), image);
        try {
            UiThreadUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    UShareHelper.share(getCurrentActivity(),
                            platform,
                            title,
                            desc,
                            shareImage,
                            url,
                            new UMShareListener() {
                                /**
                                 * 分享成功
                                 * @param share_media
                                 */
                                @Override
                                public void onResult(SHARE_MEDIA share_media) {
                                    if (callback != null) {
                                        callback.invoke(SHARE_SUCCESS);
                                    }
                                }

                                /**
                                 * 分享失败
                                 * @param share_media
                                 * @param throwable
                                 */
                                @Override
                                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                    if (callback != null) {
                                        callback.invoke(SHARE_FAILED);
                                    }
                                }

                                /**
                                 * 分享取消
                                 * @param share_media
                                 */
                                @Override
                                public void onCancel(SHARE_MEDIA share_media) {
                                    if (callback != null) {
                                        callback.invoke(SHARE_CANCEL);
                                    }
                                }
                            });
                }
            });
        } catch (Exception ex) {
            if (callback != null) {
                callback.invoke(SHARE_FAILED);
            }
            ex.printStackTrace();
        }
    }

    @ReactMethod
    public void authAndGetInfo(final String shareType, final Callback callback) {
        final SHARE_MEDIA platform = SHARE_MEDIA.convertToEmun(shareType);
        if (platform == null) {
            Log.w(MODULE_NAME, "no share type for :" + shareType);
            if (callback != null) {
                callback.invoke(SHARE_CANCEL);
            }
            return;
        }
        try {
            UiThreadUtil.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    UShareHelper.authAndGetInfo(getCurrentActivity(), platform, new UMAuthListener() {
                        @Override
                        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                            if (callback != null) {
                                WritableMap result = Arguments.createMap();
                                if (SHARE_MEDIA.WEIXIN == share_media) {
                                    result.putString("uid",map.get("unionid"));
                                    result.putString("openid",map.get("openid"));
                                }else{
                                    result.putString("uid",map.get("uid"));
                                }
                                result.putString("name",map.get("screen_name"));
                                result.putString("gender",map.get("gender"));
                                result.putString("icon",map.get("profile_image_url"));
                                callback.invoke(SHARE_SUCCESS,result);
                            }
                        }

                        @Override
                        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                            if (callback != null) {
                                callback.invoke(SHARE_FAILED);
                            }
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA share_media, int i) {
                            if (callback != null) {
                                callback.invoke(SHARE_CANCEL);
                            }
                        }
                    });
                }
            });
        } catch (Exception ex) {
            if (callback != null) {
                callback.invoke(SHARE_FAILED);
            }
            ex.printStackTrace();
        }
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("QQ", SHARE_MEDIA.QQ.name());
        constants.put("QZONE", SHARE_MEDIA.QZONE.name());
        constants.put("WEIXIN", SHARE_MEDIA.WEIXIN.name());
        constants.put("WEIXIN_CIRCLE", SHARE_MEDIA.WEIXIN_CIRCLE.name());
        constants.put("SINA", SHARE_MEDIA.SINA.name());

        constants.put("FAILED", SHARE_FAILED);
        constants.put("CANCEL", SHARE_CANCEL);
        constants.put("SUCCESS", SHARE_SUCCESS);
        return constants;
    }
}
