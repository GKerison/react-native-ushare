import {
  NativeModules,
} from  'react-native';

// 平台的类型
//   - QQ QQ
//   - QZONE QQ空间
//   - WEIXIN 微信
//   - WEIXIN_CIRCLE 朋友圈
//   - SINA 新浪微博
//
// 操作返回值
// -1 失败 FAILED
// 0 取消 CANCEL
// 1 成功 SUCCESS
//
// 授权基本信息
//  - name 昵称
//  - gender 性别
//  - icon 用户头像
//  - uid QQ、Sina （微信的unionid）
//  - openid 微信

module.exports = NativeModules.UShare;
