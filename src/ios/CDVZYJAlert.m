//
//  FMAlert.m
//  CustomAlert
//
//  Created by neo on 15/11/5.
//  Copyright © 2015年 neo. All rights reserved.
//

#import "CDVZYJAlert.h"

@interface CDVZYJAlert()

@property (nonatomic,strong) UIView *baseView;
@property (nonatomic,strong) UIImageView *icon;
@property (nonatomic,strong) UILabel *message_lab;
@property (nonatomic,assign) BOOL status;
@property (nonatomic,assign) BOOL hasStatus;

@end

@implementation CDVZYJAlert

+ (CDVZYJAlert *)showAlertWithStatus:(BOOL)status
                           hasStatus:(BOOL)hasStatus
                             message:(NSString *)message
                               delay:(CGFloat)delay
                          parentView:(UIView *)parentView {
    
    CDVZYJAlert *alert = [[CDVZYJAlert alloc]initWithMessage:message
                                                      status:status
                                                   hasStatus:hasStatus];
    alert.status = status;
    [parentView addSubview:alert];
    [alert dismiss:delay];
    return alert;
}


- (instancetype)initWithMessage:(NSString *)message
                         status:(BOOL)status
                      hasStatus:(BOOL)hasStatus {
    
    if (self = [super initWithFrame:CGRectMake(0, 0,[UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height)]){
        
        _hasStatus = hasStatus;
        
        [self loadUI];
        [self loadMessage:message];
        [self loadIconWithStatus:status];
    }
    return self;
}


- (void)loadUI {
    
    _baseView = [[UIView alloc]init];
    _baseView.center = CGPointMake([UIScreen mainScreen].bounds.size.width/2, [UIScreen mainScreen].bounds.size.height/2-20);
    _baseView.bounds = CGRectMake(0, 0, 230, 110);
    _baseView.backgroundColor = [UIColor colorWithRed:0/255.0 green:0/255.0 blue:0/255.0 alpha:0.8];
    _baseView.clipsToBounds = YES;
    _baseView.layer.cornerRadius = 10;
    [self addSubview:_baseView];
    [self show:_baseView];
}

- (void)loadMessage:(NSString *)message {

    CGSize size = [self getContentSizeWithString:message
                                            font:15
                                            size:CGSizeMake(200, 200)];
    
    _message_lab = [[UILabel alloc]init];
    _message_lab.textAlignment = NSTextAlignmentCenter;
    _message_lab.textColor = [UIColor whiteColor];
    _message_lab.text = message;
    _message_lab.font = [UIFont systemFontOfSize:15];
    _message_lab.numberOfLines = 3;
    _message_lab.lineBreakMode = 0;
    [_baseView addSubview:_message_lab];
    
    _baseView.bounds = CGRectMake(0, 0, size.width+100, size.height+70);
    if (_baseView.bounds.size.width > 230) {
        _baseView.bounds = CGRectMake(0, 0, 230, size.height + 70);
    }
    
    // 有图标的
    if (_hasStatus) {
        _message_lab.frame = CGRectMake(10,50,_baseView.bounds.size.width-20,size.height);
    }
    //
    else {
        _message_lab.frame = CGRectMake(10,30,_baseView.bounds.size.width-20,size.height);
        _baseView.bounds = CGRectMake(0, 0, size.width+100, size.height+60);
    }
}


- (void)loadIconWithStatus:(BOOL)status {
    
    if (_hasStatus) {
        _icon = [[UIImageView alloc]init];
        _icon.center = CGPointMake(_baseView.bounds.size.width/2, 25);
        _icon.bounds = CGRectMake(0, 0, 30, 30);
        [_baseView addSubview:_icon];
        if (status) {
            [_icon setImage:[UIImage imageNamed:@"right_icon"]];
        }
        else {
            [_icon setImage:[UIImage imageNamed:@"false_icon"]];
        }
    }
}

- (void)dismiss:(CGFloat)delay {
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(delay * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [self disMiss:_baseView finish:^{
            [_baseView removeFromSuperview];
            _baseView = nil;
            [self removeFromSuperview];
        }];
    });
}




- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {
    [self disMiss:_baseView finish:^{
        [_baseView removeFromSuperview];
        _baseView = nil;
        [self removeFromSuperview];
    }];
}

/**
 *  获取字符串范围大小
 *
 *  @param string 字符串
 *  @param font   字体
 *  @param size   最大范围
 *
 *  @return 获取范围
 */
- (CGSize)getContentSizeWithString:(NSString*)string font:(CGFloat)font size:(CGSize)size {
    NSDictionary *attribute = @{NSFontAttributeName: [UIFont systemFontOfSize:font]};
    CGSize rect = [string boundingRectWithSize:size options: NSStringDrawingTruncatesLastVisibleLine | NSStringDrawingUsesLineFragmentOrigin | NSStringDrawingUsesFontLeading attributes:attribute context:nil].size;
    return rect;
}
- (void)show:(UIView *)view {
    view.alpha = 0;
    CGFloat d1 = 0.2, d2 = 0.15;
    view.transform = CGAffineTransformScale(CGAffineTransformIdentity, 0.4, 0.4);
    [UIView animateWithDuration:d1 animations:^{
        view.alpha = 1;
        view.transform = CGAffineTransformScale(CGAffineTransformIdentity, 1.1, 1.1);
    } completion:^(BOOL finished) {
        [UIView animateWithDuration:d2 delay:0 options:UIViewAnimationOptionCurveEaseOut animations:^{
            view.alpha = 1;
            view.transform = CGAffineTransformScale(CGAffineTransformIdentity, 1, 1);
        } completion:^(BOOL finished2) {
        }];
    }];
}
- (void)disMiss:(UIView *)view finish:(void(^)(void))finish {
    CGFloat d1 = 0.2, d2 = 0.1;
    [UIView animateWithDuration:d2 animations:^{
        view.transform = CGAffineTransformScale(CGAffineTransformIdentity, 1.1, 1.1);
    } completion:^(BOOL finished){
        [UIView animateWithDuration:d1 delay:0 options:UIViewAnimationOptionCurveEaseOut animations:^{
            view.alpha = 0;
            view.transform = CGAffineTransformScale(CGAffineTransformIdentity, 0.4, 0.4);
        } completion:^(BOOL finished2){
            finish();
        }];
    }];
}

@end

