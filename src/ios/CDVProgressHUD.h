//
//  CDVProgressHUD.h
//
//  Created by Sam Vermette on 27.03.11.
//  Copyright 2011 Sam Vermette. All rights reserved.
//
//  https://github.com/samvermette/CDVProgressHUD
//

#import <UIKit/UIKit.h>
#import <AvailabilityMacros.h>

extern NSString * const CDVProgressHUDDidReceiveTouchEventNotification;
extern NSString * const CDVProgressHUDWillDisappearNotification;
extern NSString * const CDVProgressHUDDidDisappearNotification;
extern NSString * const CDVProgressHUDWillAppearNotification;
extern NSString * const CDVProgressHUDDidAppearNotification;

extern NSString * const CDVProgressHUDStatusUserInfoKey;

enum {
    CDVProgressHUDMaskTypeNone = 1, // allow user interactions while HUD is displayed
    CDVProgressHUDMaskTypeClear, // don't allow
    CDVProgressHUDMaskTypeBlack, // don't allow and dim the UI in the back of the HUD
    CDVProgressHUDMaskTypeGradient // don't allow and dim the UI with a a-la-alert-view bg gradient
};

typedef NSUInteger CDVProgressHUDMaskType;

@interface CDVProgressHUD : UIView

#pragma mark - Customization

+ (void)setBackgroundColor:(UIColor*)color; // default is [UIColor whiteColor]
+ (void)setForegroundColor:(UIColor*)color; // default is [UIColor blackColor]
+ (void)setRingThickness:(CGFloat)width; // default is 4 pt
+ (void)setFont:(UIFont*)font; // default is [UIFont preferredFontForTextStyle:UIFontTextStyleSubheadline]
+ (void)setSuccessImage:(UIImage*)image; // default is bundled success image from Glyphish
+ (void)setErrorImage:(UIImage*)image; // default is bundled error image from Glyphish

#pragma mark - Show Methods

+ (void)show:(void(^)(void))show;
+ (void)showWithMaskType:(CDVProgressHUDMaskType)maskType;
+ (void)showWithStatus:(NSString*)status;
+ (void)showWithStatus:(NSString*)status maskType:(CDVProgressHUDMaskType)maskType;

+ (void)showProgress:(float)progress;
+ (void)showProgress:(float)progress status:(NSString*)status;
+ (void)showProgress:(float)progress status:(NSString*)status maskType:(CDVProgressHUDMaskType)maskType;

+ (void)setStatus:(NSString*)string; // change the HUD loading status while it's showing

// stops the activity indicator, shows a glyph + status, and dismisses HUD 1s later
+ (void)showSuccessWithStatus:(NSString*)string;
+ (void)showErrorWithStatus:(NSString *)string;
+ (void)showImage:(UIImage*)image status:(NSString*)status; // use 28x28 white pngs

+ (void)setOffsetFromCenter:(UIOffset)offset;
+ (void)resetOffsetFromCenter;

+ (void)popActivity;
+ (void)dismiss:(void (^)(void))dismiss;

+ (BOOL)isVisible;

@end
