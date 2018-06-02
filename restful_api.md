# APIs List

- POST /message  : send a email message to queue
- GET /message   : get the queue status
- POST /history  : get the email delivery history
- GET /provider    : get all registered providers
- POST /provider   : register provider
- DELETE /provider : unregister provider
- POST /provider/mailgun : request mailgun adapter to send email
- GET /provider/mailgun  : get mailgun status
- POST /provider/sendgrid : request sendgrid adapter to send email
- GET /provider/sendgrid: get sendgrid status

# APIs Details

### Add Message To Queue

Used to collect a Token for a registered User.

**URL** : `/message`

**Method** : `POST`

**Auth required** : NO

**Data constraints**

```json
{
    "from": "[valid email address]",
    "to": "[valid email address]",
    "cc": "[valid email address, optional]",
    "bcc": "[valid email address, optional]",
    "title": "[plain text]",
    "message": "[plain text]"
}
```

**Data example**

```json
{
    "from": "test@example.com",
    "to": "test@example.com, test2@example.com",
    "cc": "test@example.com, test2@example.com",
    "bcc": "test@example.com, test2@example.com",
    "title": "Hello",
    "message": "World"
}
```

#### Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "dateTime": "2018-05-29T09:24:12.772+0000",
    "returnCode": 0,
    "message": "Email has been successfully queued."
}
```

#### Error Response

**Condition** : if anything is wrong.

**Code** : `400 BAD REQUEST`

**Content** :

```json
{
    "dateTime": "2018-05-29T09:24:12.772+0000",
    "returnCode": -1,
    "message": "Failed to add email to queue!"
}
```
or

```json
{
    "dateTime": "2018-05-29T09:24:12.772+0000",
    "returnCode": -2,
    "message": "Invalid Receiver Email: Invalid$gmail.com"
}
```

//todo: add more api documentation here.
