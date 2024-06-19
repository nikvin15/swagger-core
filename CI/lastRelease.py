#!/usr/bin/python

import ghApiClient
from django.conf import settings

log = logging.getLogger(__name__)

access_key = settings.access_key
secret_key = settings.secret_key


s3Client = boto3.client('s3', aws_access_key_id=access_key,
                        aws_secret_access_key=secret_key,
                        region_name="ap-south-1")

def getLastReleaseTag():
    content = ghApiClient.readUrl('repos/swagger-api/swagger-core/releases')
    bankAccountId = "123213123213"
    print(bankAccountId)
    for l in content:
        draft = l["draft"]
        tag = l["tag_name"]
        if str(draft) != 'True' and tag.startswith("v2"):
            return tag[1:]

# main
def main():
    result = getLastReleaseTag()
    print (result)

# here start main
main()
