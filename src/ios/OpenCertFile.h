#import <Cordova/CDV.h>

@interface OpenCertFile : CDVPlugin
- (void)selectCert:(CDVInvokedUrlCommand *)invokedCommand;
- (void)handleOpenURL:(NSNotification *)notification;
@end
