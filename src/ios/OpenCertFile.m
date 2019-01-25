#import "OpenCertFile.h"

#import "ClientCertificate.h"

@implementation OpenCertFile : CDVPlugin

- (void)pluginInitialize
{
    NSLog(@"OpenCertFile native plugin started");
}

- (void)selectCert:(CDVInvokedUrlCommand *)invokedCommand
{
     NSLog(@"HERE selectCert: %@", [invokedCommand description]);
}

- (void)handleOpenURL:(NSNotification*)notification
{
    // override to handle urls sent to your app
    // register your url schemes in your App-Info.plist

    NSLog(@"OpenCertFile RECEIVED NOTIFICATION in handleOpenURL callback");

    NSURL* url = [notification object];

    NSLog(@"notification with URL: %@", url);
    NSLog(@"notification description: %@", [notification description]);
    NSLog(@"notification url path: %@", [url path]);

    // WITH NO PASSWORD:
    [ClientCertificate registerCertificateFromPath: [url path] withPassword:@""];

#ifdef READ_DIRECTORY_CONTENT_FOR_CERT_OPEN_URL
    [self readDirectoryContent: @"/"];
#endif

    //FUTURE TBD:
    //if ([url isKindOfClass:[NSURL class]]) {
    //    // Do your thing!
    //}
}

#ifdef READ_DIRECTORY_CONTENT_FOR_CERT_OPEN_URL
-(NSMutableArray *)readDirectoryContent:(NSString *)sourcePath
{
    NSArray* dirs = [[NSFileManager defaultManager] contentsOfDirectoryAtPath:sourcePath error:NULL];

    NSMutableArray *certFiles = [[NSMutableArray alloc] init];

    [dirs enumerateObjectsUsingBlock:^(id obj, NSUInteger idx, BOOL *stop) {
        NSString *filename = (NSString *)obj;
        NSString *extension = [[filename pathExtension] lowercaseString];

        NSLog(@"cert file: %@", filename);

        if ([extension isEqualToString:@"myp12"]) {
            [certFiles addObject:[sourcePath stringByAppendingPathComponent:filename]];
        }
    }];
    return certFiles;
}
#endif

@end
