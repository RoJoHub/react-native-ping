//
//  GBPingSummary.h
//

#import <Foundation/Foundation.h>

@interface GBPingSummary : NSObject <NSCopying>

typedef enum {
    GBPingStatusPending,
    GBPingStatusSuccess,
    GBPingStatusFail,
} GBPingStatus;

@property (assign, nonatomic) NSUInteger sequenceNumber;
@property (assign, nonatomic) NSUInteger payloadSize;
@property (assign, nonatomic) NSUInteger ttl;
@property (strong, nonatomic, nullable) NSString *host;
@property (strong, nonatomic, nullable) NSDate *sendDate;
@property (strong, nonatomic, nullable) NSDate *receiveDate;
@property (assign, nonatomic) NSTimeInterval rtt;
@property (assign, nonatomic) GBPingStatus status;

@end
