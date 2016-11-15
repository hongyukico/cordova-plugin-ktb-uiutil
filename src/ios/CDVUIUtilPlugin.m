//
//  AlertPlugin.m
//  MyApp
//
//  Created by zyj7815 on 16/9/29.
//
//

#import "CDVUIUtilPlugin.h"
#import "CDVZYJAlert.h"
#import "CDVSTAlertView.h"
#import "CDVProgressHUD.h"

@implementation CDVUIUtilPlugin

#pragma mark - cordova 调用native控制台输出
- (void)openAlert:(CDVInvokedUrlCommand *)command {
    NSDictionary *dict = command.arguments[0];
    
    BOOL status;
    BOOL hasStatus = YES;
    
    // 如果有状态
    if (dict[@"status"]) {
       status = [dict[@"status"] boolValue];
    }
    // 没有状态
    else {
        hasStatus = NO;
    }
    
    CGFloat duration = 1.5;
    if (dict[@"duration"]) {
        duration = [dict[@"duration"] doubleValue];
    }
    
    NSString *message = dict[@"message"];
    
    
    [CDVZYJAlert showAlertWithStatus:status
                           hasStatus:hasStatus
                             message:message
                               delay:duration
                          parentView:self.viewController.view];
}


#pragma mark - 打开一个带按钮的提示框
- (void)openConfirmAlert:(CDVInvokedUrlCommand *)command {
    
    NSDictionary *dict = command.arguments[0];
    NSString *title = dict[@"title"];
    NSString *message = dict[@"message"];
    NSArray *button = dict[@"button"];
    
    [CDVSTAlertView showTitle:title
                        image:nil
                      message:message
                 buttonTitles:button
                      handler:^(NSInteger index)
    {
        dispatch_async(dispatch_get_main_queue(), ^{
            CDVPluginResult *pluginResult = nil;
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                                messageAsInt:(int)index];
            [self.commandDelegate sendPluginResult:pluginResult
                                        callbackId:command.callbackId];
            [self.commandDelegate runInBackground:^{}];
        });
    }];
}


#pragma mark - 打卡/关闭loading
- (void)openLoading:(CDVInvokedUrlCommand *)command {
    NSDictionary *dict = command.arguments[0];
    BOOL loading = [dict[@"isLoading"] boolValue];
    if (loading == 1) {
        [CDVProgressHUD show:^{
//            self.webView.userInteractionEnabled = NO;
        }];
    } else {
        [CDVProgressHUD dismiss:^{
//            self.webView.userInteractionEnabled = YES;
        }];
    }
}

@end
