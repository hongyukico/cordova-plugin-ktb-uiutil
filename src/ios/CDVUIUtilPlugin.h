//
//  AlertPlugin.h
//  MyApp
//
//  Created by zyj7815 on 16/9/29.
//
//

#import <Cordova/CDVPlugin.h>

@interface CDVUIUtilPlugin : CDVPlugin


/**
 打开一个简单的提醒框
 */
- (void)openAlert:(CDVInvokedUrlCommand *)command;


/**
 打开一个带按钮的提示框
 */
- (void)openConfirmAlert:(CDVInvokedUrlCommand *)command;


/**
 打卡/关闭loading
 */
- (void)openLoading:(CDVInvokedUrlCommand *)command;

@end
