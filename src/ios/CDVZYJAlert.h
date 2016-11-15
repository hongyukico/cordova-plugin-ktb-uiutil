//
//  FMAlert.h
//  CustomAlert
//
//  Created by neo on 15/11/5.
//  Copyright © 2015年 neo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CDVZYJAlert : UIView


+ (CDVZYJAlert *)showAlertWithStatus:(BOOL)status
                           hasStatus:(BOOL)hasStatus
                             message:(NSString *)message
                               delay:(CGFloat)delay
                          parentView:(UIView *)parentView;


@end

