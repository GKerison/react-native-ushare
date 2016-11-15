//
//  UShareHelper.m
//  UShareExample
//
//  Created by k on 2016/11/10.
//  Copyright © 2016年 Facebook. All rights reserved.
//

#import "UShareHelper.h"

@implementation UShareHelper

+(void)openLog:(BOOL)isOpen{
  [[UMSocialManager defaultManager] openLog:isOpen];
}

+(void)configUShare:(NSString *)appKey{
  [[UMSocialManager defaultManager] setUmSocialAppkey:appKey];
}

+(void)configWeiXin:(NSString *)appKey appSecret:(NSString *)appSecret{
  [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_WechatSession appKey:appKey appSecret:appSecret redirectURL:@"http://mobile.umeng.com/social"];
  
}

+(void)configQQ:(NSString *)appId{
  [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_QQ appKey:appId  appSecret:nil redirectURL:@"http://mobile.umeng.com/social"];
}

+(void)configSina:(NSString *)appKey appSecret:(NSString *)appSecret{
  [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_Sina appKey:appKey  appSecret:appSecret redirectURL:@"http://sns.whalecloud.com/sina2/callback"];
}

+(BOOL)handleOpenURL:(NSURL *)url{
  return [[UMSocialManager defaultManager] handleOpenURL:url];
}

+(void)share:(NSString *)shareType title:(NSString *)title desc:(NSString *)desc image:(NSString *)image url:(NSString *)url callback:(UMSocialRequestCompletionHandler)callback{
  
  UMSocialMessageObject *messageObject = [UMSocialMessageObject messageObject];
  if(url){
    UMShareWebpageObject *webObject = [UMShareWebpageObject shareObjectWithTitle:title descr:desc thumImage:image];
    webObject.webpageUrl = url;
    messageObject.shareObject =webObject;
  }else{
    UMShareImageObject *imageObject = [UMShareImageObject shareObjectWithTitle:title descr:desc thumImage:image];
    messageObject.shareObject =imageObject;
  }
  //调用分享接口
  dispatch_async(dispatch_get_main_queue(), ^{
    [[UMSocialManager defaultManager] shareToPlatform:UMSocialPlatformType_QQ messageObject:messageObject currentViewController:nil completion:callback];
  });
}

@end
