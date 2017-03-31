#import "OpenCertFile.h"

@implementation OpenCertFile : CDVPlugin

- (void)selectCert:(CDVInvokedUrlCommand *)invokedCommand
{
     printf("HERE selectCert: %s", [[invokedCommand description] UTF8String]);
}

- (void)handleOpenURL:(NSNotification*)notification
{
    // override to handle urls sent to your app
    // register your url schemes in your App-Info.plist

    NSURL* url = [notification object];

    printf("HERE Notification: %s", [[notification description] UTF8String]);
    [self readDirectoryContent: @"/"];

    if ([url isKindOfClass:[NSURL class]]) {
        /* Do your thing! */
    }
}

-(NSMutableArray *)readDirectoryContent:(NSString *)sourcePath
{
    NSArray* dirs = [[NSFileManager defaultManager] contentsOfDirectoryAtPath:sourcePath error:NULL];
    NSMutableArray *certFiles = [[NSMutableArray alloc] init];
    [dirs enumerateObjectsUsingBlock:^(id obj, NSUInteger idx, BOOL *stop) {
        NSString *filename = (NSString *)obj;
        NSString *extension = [[filename pathExtension] lowercaseString];
        printf("HERE File: %s", [filename UTF8String]);
        if ([extension isEqualToString:@"myp12"]) {
            [certFiles addObject:[sourcePath stringByAppendingPathComponent:filename]];
        }
    }];
    return certFiles;
}

@end
