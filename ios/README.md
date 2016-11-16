## iOS配置指南

1. 项目右键添加或者直接把文件夹拖入项目中。

  npm install后，在node_modules中找到react-native-ushare-kerison>ios>UMSocial。

2. 添加项目配置

  BuildSettings > Linking > Other Linker Flags 支持-ObjC

3. 添加平台依赖

  - SystemConfiguration.framework
  - CoreTelephony.framework
  - ImageIO.framework
  - libsqlite3.tbd
  - libc++.tbd
  - libz.tbd

4. 配置平台URL Scheme

  [传送门](http://dev.umeng.com/social/ios/quick-integration#2_1)

5. 添加适配

  只需要添加QQ,微信，新浪部分就够了 [传送门](http://dev.umeng.com/social/ios/ios9)

6. 配置Key

  ```
  [UShareHelper configLog:BOOL];
  [UShareHelper configUShare:UMENG_KEY];
  [UShareHelper configQQ:appID];
  [UShareHelper configWeiXin:appKey appSecret:appSecret];
  [UShareHelper configSina:appKey appSecret:appSecret];
  ```
7. 配置回调拦截
  ```
  -(BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url{
    return [UShareHelper handleOpenURL:url];
  }

  -(BOOL) application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options{
    return [UShareHelper handleOpenURL:url];
  }
  ```
