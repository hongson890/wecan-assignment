
# Wecan Voucher Assignment - Sonny

In this assignment, I'm going build a Java Spring Boot Application from scratch, which contains some following features:

# Features

- Provide management interface, which enables the operators to setup a set of vouchers with various
  features
- Support 4 types of redemption: Single redemption, Multiple redemption, X times redemption, before a certain point of time redemption
- Provide redemption interface, which enables the client redeem a single voucher


# Tech Stack

**Language:** Java 8

**Framework:** Spring Boot, Spring JPA, Liquibase, JUnit4, Swagger, Dockerization

**Database:** PostgresSQL

# Source Directory
    src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── wecan
    │   │           └── assignment
    │   │               ├── common
    │   │               │   ├── RedemptionType.java
    │   │               │   └── VoucherType.java
    │   │               ├── controllers
    │   │               │   ├── api
    │   │               │   │   ├── RedemptionController.java
    │   │               │   │   └── VoucherController.java
    │   │               │   └── dto
    │   │               │       ├── VoucherDTO.java
    │   │               │       └── VoucherRequestDTO.java
    │   │               ├── exception
    │   │               │   ├── VoucherBadParamsException.java
    │   │               │   ├── VoucherExceptionControllerAdvice.java
    │   │               │   ├── VoucherExistedException.java
    │   │               │   └── VoucherNotfoundException.java
    │   │               ├── mapper
    │   │               │   └── VoucherMapper.java
    │   │               ├── model
    │   │               │   ├── Redemption.java
    │   │               │   └── Voucher.java
    │   │               ├── repositories
    │   │               │   ├── RedemptionRepository.java
    │   │               │   └── VoucherRepository.java
    │   │               ├── services
    │   │               │   ├── impl
    │   │               │   │   ├── RedemptionServiceImpl.java
    │   │               │   │   └── VoucherServiceImpl.java
    │   │               │   ├── RedemptionService.java
    │   │               │   └── VoucherService.java
    │   │               ├── utils
    │   │               │   ├── DateUtils.java
    │   │               │   └── VoucherUtil.java
    │   │               └── AssignmentApplication.java
    │   └── resources
    │       ├── db
    │       │   └── changelog
    │       │       ├── db.changelog-1.0.xml
    │       │       ├── db.changelog-1.1.xml
    │       │       └── db.changelog-master.yaml
    │       ├── application.yml
    │       └── logback.xml
    └── test
        └── java
            └── com
                └── wecan
                    └── assignment
                        └── services
                            ├── RedemptionServiceImplTest.java
                            └── VoucherServiceImplTest.java


# API Reference

## (1) Voucher Interface

#### Get all vouchers

```http
  GET /api/vouchers
```


#### Get voucher by id

```http
  GET /api/vouchers/${id}
```

| Path Variable | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. ID of voucher to fetch |

#### Create a new voucher

```http
  POST /api/vouchers
```
Request Body:

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `String` | **Required**. Code of voucher |
| `name`      | `String` | **Required**. Title of voucher |
| `redemptionType`      | `String` | **Required**. Redemption type of voucher. There are 4 different types: `SINGLE`, `MULTIPLE`, `X_TIMES`, `BEFORE_CERTAIN_POINT_OF_TIME` |
| `redemptionValue`      | `String` | **Required**. Redemption value of voucher, which based on redemptionType. i.e "10", "2030-06-24T07:55:02Z" |
| `voucherType`      | `String` | **Required**. Type of voucher. There are 2 different types: `FIXED_AMOUNT`, `PERCENTAGE`  |
| `voucherContent`      | `String` | **Required**. Content of the voucher, which's based on voucher type. i.e: "10%"|


#### Update a voucher

```http
  PUT /api/vouchers/{id}
```
| Path Variable | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. ID of voucher to fetch |

Request Body:

| Parameter                 | Type     | Description                       |
| :-------------------------| :------- | :-------------------------------- |
| `code`                    | `String` | **Required**. Code of voucher |
| `name`                    | `String` | **Required**. Title of voucher |
| `redemptionType`          | `String` | **Required**. Redemption type of voucher. There are 4 different types: `SINGLE`, `MULTIPLE`, `X_TIMES`, `BEFORE_CERTAIN_POINT_OF_TIME` |
| `redemptionValue`         | `String` | **Required**. Redemption value of voucher, which based on redemptionType. i.e "10", "2030-06-24T07:55:02Z" |
| `voucherType`             | `String` | **Required**. Type of voucher. There are 2 different types: `FIXED_AMOUNT`, `PERCENTAGE`  |
| `voucherContent`          | `String` | **Required**. Content of the voucher, which's based on voucher type. i.e: "10%"|
| `active`                  | `boolean`| **Required**. Status of the voucher. True if enable. False if Disabled. |
| `redemptionTimes`         | `Integer`| Number of redemptions on this voucher|


#### Delete a voucher

```http
  DELETE /api/vouchers/{id}
```
| Path Variable | Type     | Description                        |
| :-------- | :------- | :--------------------------------      |
| `id`      | `Integer` | **Required**. ID of voucher to fetch  |


## (2) Redemption Interface

#### Redeem a voucher

```http
  POST /api/redemption/{voucherId}
```
| Path Variable     | Type      | Description                            |
| :-----------------| :-------  | :--------------------------------------|
| `voucherId`       | `Integer` | **Required**. ID of voucher to redeem  |
# Voucher Check Availability Flow

![ScreenShot](https://live.staticflickr.com/65535/52168568632_015a1d25c6_b.jpg)

# Installation & Starting

>   Run Unit Test
```
mvn test
```
 

> Packaging
```
./mvnw clean package 
```

>   Start project from source code if you already setup a Postgres DB
```
mvn spring:boot-run
```

>   Start by docker if you haven't setuped Postgres DB yet.
```
# Build Docker Image
docker build -t sonny-wecan-assignment .

# Start by using docker compose
docker-compose up
```
# Demo


>   API Document
```
http://localhost:8000/swagger-ui.html#/

```

![ScreenShot](https://live.staticflickr.com/65535/52169588823_8efe2ddfd3_k.jpg)


1. _To get all vouchers_


```
curl --location --request GET 'http://localhost:8000/api/vouchers'
```

2. _To generate a voucher_

```
curl --location --request POST 'http://localhost:8000/api/vouchers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "test voucher",
    "code": "BETA",
    "redemptionType": "X_TIMES",
    "redemptionValue": "10",
    "voucherType": "FIXED_AMOUNT",
    "voucherContent": "100",
    "active": true
}'
```

3. _To update existed voucher_

```
curl --location --request PUT 'http://localhost:8000/api/vouchers/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "test voucher",
    "code": "BETA",
    "redemptionType": "X_TIMES",
    "redemptionValue": "2",
    "voucherType": "FIXED_AMOUNT",
    "voucherContent": "100",
    "active": true
}'
```


4. _To delete a voucher_
```
curl --location --request DELETE 'http://localhost:8000/api/vouchers/1'
```

5. _To get a voucher by Id_

```
curl --location --request GET 'http://localhost:8000/api/vouchers/1'
```


6. _To redeem a voucher_

```
    curl --location --request POST 'http://localhost:8000/api/redemption/1'
```
