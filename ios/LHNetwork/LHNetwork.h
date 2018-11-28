//
//  LHNetwork.h
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface LHNetwork : NSObject

@property (nonatomic, copy, readonly) NSString *receivedNetworkSpeed;

@property (nonatomic, copy, readonly) NSString *sendNetworkSpeed;

@property (nonatomic, copy, readonly) NSString *receivedNetworkTotal;

@property (nonatomic, copy, readonly) NSString *sendNetworkTotal;
+ (instancetype)shareNetworkSpeed;

- (void)startMonitoringNetwork;

- (void)stopMonitoringNetwork;

- (void)checkNetworkflow;
@end

/**
 *  @{@"received":@"100kB/s"}
 */
FOUNDATION_EXTERN NSString *const kNetworkReceivedSpeedNotification;

/**
 *  @{@"send":@"100kB/s"}
 */
FOUNDATION_EXTERN NSString *const kNetworkSendSpeedNotification;
