# Tweet Controller

- [Get Account Tweets](#get-account-tweets)
- [Get Account Tweet by ID](#get-account-tweet-by-id)
- [Post Tweet](#post-tweet)
- [Update Tweet](#update-tweet)

---

## <a name="get-account-tweets"></a> Get Account Tweets

**Http Method**: `GET`

**Endpoint**: `/api/v1/tweets`

**Request Param**:

| Request Param | Data Type       |
|---------------|-----------------|
| pageNumber    | int, default: 0 |
| pageSize      | int, default: 5 |

**Request Body**:

| Request Body | Data Type |
|--------------|-----------|
| username     | String    |

**Response**:

```json
{
  "username": "johndoe"
}
```

- response sample &rarr; `/tweets?pageNumber=0&pageSize=5`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [
    {
      "id": "47564903-21c3-4d78-9d00-407e9709c34a",
      "createdDate": "2023-03-18T08:24:45.064+00:00",
      "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "updatedDate": "2023-03-18T08:24:45.064+00:00",
      "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "message": "The sun is shining and the birds are singing. What a beautiful day!"
    },
    {
      "id": "36cda56d-7d7d-4c66-98ae-1ad1aee5761c",
      "createdDate": "2023-03-18T08:24:45.064+00:00",
      "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "updatedDate": "2023-03-18T08:24:45.064+00:00",
      "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "message": "I just finished a great book and I'm already looking for my next read."
    },
    {
      "id": "92f82f1f-b858-403f-9a47-0e86aee1abe2",
      "createdDate": "2023-03-18T08:24:45.064+00:00",
      "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "updatedDate": "2023-03-18T08:24:45.064+00:00",
      "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "message": "I'm so grateful for my friends and family. They always know how to make me smile."
    },
    {
      "id": "d0ddccdc-4611-4794-8f8f-97aa94e51c40",
      "createdDate": "2023-03-18T08:24:45.064+00:00",
      "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "updatedDate": "2023-03-18T08:24:45.064+00:00",
      "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "message": "Sometimes the best thing to do is just take a deep breath and let it go."
    },
    {
      "id": "52f025d2-fe8a-402e-afaa-ae4b7cd4c3ba",
      "createdDate": "2023-03-18T08:24:45.065+00:00",
      "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "updatedDate": "2023-03-18T08:24:45.065+00:00",
      "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
      "message": "I can't believe how fast time flies. It feels like just yesterday I was starting this job."
    }
  ],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalRecords": 15
  }
}
```

- response sample &rarr; empty `content`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "content": [],
  "pageMetaData": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalRecords": 0
  }
}
```

- response sample &rarr; `username` null

```json
{
  "code": 403,
  "status": "BAD_REQUEST",
  "error": "username must not be null"
}
```

- response sample &rarr; `username` blank

```json
{
  "code": 403,
  "status": "BAD_REQUEST",
  "error": "username must not be blank"
}
```

- response sample &rarr; `username` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "account not found"
}
```

- response sample &rarr; Server Error

```json
{
  "code": 503,
  "status": "SERVICE_UNAVAILABLE",
  "error": "service temporarily unavailable"
}
```

## <a name="get-account-tweet-by-id"></a> Get Account Tweet by ID

**Http Method**: `GET`

**Endpoint**: `/api/v1/tweets/{tweetId}`

**Request Param**:

| Request Param | Data Type |
|---------------|-----------|
| tweetId       | String    |

**Request Body**:

```json
{
  "username": "johndoe"
}
```

**Response**:

- response sample &rarr; `/tweets/2361871c-a647-4ed1-ba5b-0ed7f99e623a`

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "data": {
    "id": "2361871c-a647-4ed1-ba5b-0ed7f99e623a",
    "createdDate": "2023-03-18T08:30:17.543+00:00",
    "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
    "updatedDate": "2023-03-18T08:30:17.543+00:00",
    "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
    "message": "The sun is shining and the birds are singing. What a beautiful day!"
  }
}
```

- response sample &arr; `username` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "account not found"
}
```

- response sample &rarr; `tweetId` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "tweet not found"
}
```

- response sample &rarr; Server Error

```json
{
  "code": 503,
  "status": "SERVICE_UNAVAILABLE",
  "error": "service temporarily unavailable"
}
```

## <a name="post-tweet"></a> Post Tweet

**Http Method**: `POST`

**Endpoint**: `/api/v1/tweets`

**Request Param**: -

**Request Body**:

```json
{
  "username": "johndoe",
  "message": "The sun is shining and the birds are singing. What a beautiful day! 🌟 #motivation #inspiration"
}
```

**Response**:

- response sample

```json
{
  "code": 200,
  "status": "OK",
  "error": null,
  "data": {
    "id": "cc49c09a-888c-4ea8-89e4-8df0228e381f",
    "createdDate": "2023-03-18T08:34:10.999+00:00",
    "createdBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
    "updatedDate": "2023-03-18T08:34:10.999+00:00",
    "updatedBy": "2fbdf929-7252-4400-8bbe-79bd2bc5d0f8",
    "message": "The sun is shining and the birds are singing. What a beautiful day! 🌟 #motivation #inspiration"
  }
}
```

- response sample &rarr; `username` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "account not found"
}
```

- response sample &rarr; Server Error

```json
{
  "code": 503,
  "status": "SERVICE_UNAVAILABLE",
  "error": "service temporarily unavailable"
}
```

## <a name="update-tweet"></a> Update Tweet

**Http Method**: `PUT`

**Endpoint**: `/api/v1/tweets/{tweetId}`

**Request Param**: -

**Request Body**:

```json
{
  "username": "johndoe",
  "message": "The sun is shining and the birds are singing. What a beautiful day! 🌟 #motivation #inspiration"
}
```

**Response**:

- response sample &rarr; `/tweets/c2a86195-8e40-402c-ba1c-16308ee672fa`

```json
{
  "code": 200,
  "status": "OK",
  "data": {
    "id": "c2a86195-8e40-402c-ba1c-16308ee672fa",
    "createdDate": "2023-03-22T03:16:18.731869",
    "createdBy": "bb36c86d-7f03-4cc8-8798-47cd7427cba8",
    "updatedDate": "2023-03-23T04:49:40.967183",
    "updatedBy": "bb36c86d-7f03-4cc8-8798-47cd7427cba8",
    "message": "Updated tweet message"
  }
}
```

- response sample &rarr; `username` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "account not found"
}
```

- response sample &rarr; `tweetId` not found

```json
{
  "code": 404,
  "status": "NOT_FOUND",
  "error": "tweet not found"
}
```

- response sample &rarr; Server Error

```json
{
  "code": 503,
  "status": "SERVICE_UNAVAILABLE",
  "error": "service temporarily unavailable"
}
```