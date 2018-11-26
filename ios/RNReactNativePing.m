
#import "RNReactNativePing.h"
#import "GBPing.h"

@interface RNReactNativePing ()
@property (nonatomic, strong) GBPing *ping;
@end
@implementation RNReactNativePing

RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(
    start:(NSString *)ipAddress
    resolver:(RCTPromiseResolveBlock)resolve
    rejecter:(RCTPromiseRejectBlock)reject
    ) {
    self.ping.host = ipAddress;
    __weak __typeof(self)weakSelf = self;

    [self.ping setupWithBlock:^(BOOL success, NSError *_Nullable error) { //necessary to resolve hostname
        if (!success) {
            reject(@"-1", @"域名解析失败", error);
            return;
        }
        
        [weakSelf.ping startPingingWithBlock:^(GBPingSummary *summary) {
            resolve(@(@(summary.rtt*1000).intValue));
            [weakSelf.ping stop];
            weakSelf.ping=nil;
        } fail:^(NSError *_Nonnull error) {
            reject(@"-1", @"ping失败", error);
            [weakSelf.ping stop];
            weakSelf.ping=nil;
        }];

    }];
}
- (GBPing *)ping
{
    if (!_ping) {
        _ping = [[GBPing alloc] init];
        _ping.timeout = 1.0;
        _ping.pingPeriod = 0.9;
    }
    return _ping;
}

@end
