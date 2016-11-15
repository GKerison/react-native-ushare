//
//  UShareHelper.h
//  UShareExample
//
//  Created by k on 2016/11/10.
//  Copyright © 2016年 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UMSocialCore/UMSocialCore.h>

@interface UShareHelper : NSObject

+(void)openLog:(BOOL)isOpen;

+(void)configUShare:(NSString *)appKey;

+(void)configWeiXin:(NSString *)appKey appSecret:(NSString *)appSecret;

+(void)configQQ:(NSString *)appId;

+(void)configSina:(NSString *)appKey  appSecret:(NSString *)appSecret;

+(BOOL)handleOpenURL:(NSURL *)url;

+(void)share:(NSString *)shareType title:(NSString *)title desc:(NSString *)desc image:(NSString*) image url:(NSString *)url callback:(UMSocialRequestCompletionHandler)callback;

@end
