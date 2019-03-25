
#import "RNReactNativePing.h"
#import "GBPing.h"
#import "LHNetwork.h"

@interface RNReactNativePing ()

@property (nonatomic, strong) GBPing *ping;

@end

@implementation RNReactNativePing
{
    bool hasListeners;
    dispatch_queue_t queue;
}

RCT_EXPORT_MODULE()
- (dispatch_queue_t)methodQueue
{
    if (!queue) {
        queue = dispatch_queue_create("com.pomato.React.RNReactNativePing", DISPATCH_QUEUE_SERIAL);
    }
    return queue;
}

RCT_EXPORT_METHOD(
    start:(NSString *)ipAddress
    resolver:(RCTPromiseResolveBlock)resolve
    rejecter:(RCTPromiseRejectBlock)reject
    ) {
    self.ping.host = ipAddress;
    __weak __typeof(self)weakSelf = self;

    [self.ping setupWithBlock:^(BOOL success, NSError *_Nullable error) { //necessary to resolve hostname
        if (!success) {
            reject(@"-1", error.domain, error);
            return;
        }

        [weakSelf.ping startPingingWithBlock:^(GBPingSummary *summary) {
            resolve(@(@(summary.rtt * 1000).intValue));
            [weakSelf.ping stop];
            weakSelf.ping = nil;
        } fail:^(NSError *_Nonnull error) {
            reject(@"-1", error.domain, error);
            [weakSelf.ping stop];
            weakSelf.ping = nil;
        }];
    }];
}
RCT_REMAP_METHOD(
    getTrafficStats,
    resolver:(RCTPromiseResolveBlock)resolve
    rejecter:(RCTPromiseRejectBlock)reject
    ) {
    LHNetwork *instance = [LHNetwork shareNetworkSpeed];
    [instance checkNetworkflow];
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1 * NSEC_PER_SEC)), queue, ^{
        [instance checkNetworkflow];

        NSString *receivedNetworkSpeed = instance.receivedNetworkSpeed;
        NSString *receivedNetworkTotal = instance.receivedNetworkTotal;
        NSString *sendNetworkSpeed = instance.sendNetworkSpeed;
        NSString *sendNetworkTotal = instance.sendNetworkTotal;
        resolve(@{
            @"receivedNetworkSpeed": receivedNetworkSpeed,
            @"receivedNetworkTotal": receivedNetworkTotal,
            @"sendNetworkSpeed": sendNetworkSpeed,
            @"sendNetworkTotal": sendNetworkTotal
        });
    });
}
#pragma mark -
#pragma mark Getter
#pragma mark -
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
