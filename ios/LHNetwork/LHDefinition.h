//
//  LHDefinition.h
//  RNReactNativePing
//
//  Created by Jerry-Luo on 2019/3/29.
//  Copyright © 2019 Pomato. All rights reserved.
//

#ifndef LHDefinition_h
#define LHDefinition_h

#define DEFINE_NSError(errorName,description) NSError *errorName = [NSError errorWithDomain:@#description code:description userInfo:@{@"code":@(description),@"message":@#description}];

typedef NS_ENUM (NSUInteger, PingErrorCode) {
    PingUtil_Message_Timeout,
    PingUtil_Message_PreviousPingIsStillRunning,
    PingUtil_Message_HostErrorNotSetHost,
    PingUtil_Message_HostErrorUnknown,
    PingUtil_Message_HostErrorHostNotFound,
    PingUtil_Message_Unknown,
};


#endif /* LHDefinition_h */
