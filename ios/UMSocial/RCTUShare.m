//
//  RCTUShare.m
//  UShareExample
//
//  Created by k on 2016/11/10.
//  Copyright © 2016年 Facebook. All rights reserved.
//

#import "RCTUShare.h"

#define SHARE_FAILED "-1"
#define SHARE_SUCCESS "1"

@implementation RCTUShare

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(share:(NSString *)shareType title:(NSString *)title desc:(NSString *)desc image:(NSString*) image url:(NSString *)url callback:(RCTResponseSenderBlock)callback )
{
  UMSocialPlatformType platformType = UMSocialPlatformType_UnKnown;
  if([@"QQ" isEqualToString:shareType]){
    platformType = UMSocialPlatformType_QQ;
  }else if([@"QZONE" isEqualToString:shareType]){
    platformType = UMSocialPlatformType_Qzone;
  }else if([@"WEIXIN" isEqualToString:shareType]){
    platformType = UMSocialPlatformType_WechatSession;
  }else if([@"WEIXIN_CIRCLE" isEqualToString:shareType]){
    platformType = UMSocialPlatformType_WechatTimeLine;
  }else if([@"SINA" isEqualToString:shareType]){
    platformType = UMSocialPlatformType_Sina;
  }else{
    platformType = UMSocialPlatformType_UnKnown;
  }
  
  //不支持的平台直接返回失败
  if(UMSocialPlatformType_UnKnown == platformType){
    if(callback){
      callback(@[@SHARE_FAILED]);
    }
    return;
  }
  
  
  [UShareHelper share:shareType title:title desc:desc image:image url:url callback:^(id result, NSError *error) {
    if(!callback){
      return;
    }
    if (error) {
      callback(@[@SHARE_FAILED]);
    }else{
      callback(@[@SHARE_SUCCESS]);
    }
  }];
}

- (NSDictionary *)constantsToExport
{
  return @{
           @"QQ": @"QQ",
           @"QZONE": @"QZONE",
           @"WEIXIN": @"WEIXIN",
           @"WEIXIN_CIRCLE": @"WEIXIN_CIRCLE",
           @"SINA": @"SINA",
           
           @"FAILED": @SHARE_FAILED,
           @"CANCEL": @SHARE_FAILED,//ios取消和失败是同样的操作
           @"SUCCESS": @SHARE_SUCCESS
           };
}
@end
