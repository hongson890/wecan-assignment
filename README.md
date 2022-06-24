
# Wecan Voucher Assignment - Sonny

A brief description of what this project does and who it's for


# Features

- Proive management interface, which enables the operators to setup a set of vouchers with various
  features
- Support 4 types of redemption: Single redemption, Multiple redemption, X times redemption, before a certain point of time redemption
- Provide redemption interface, which enables the client redeem a single voucher


# Tech Stack

**Language:** Java 8

**Framework:** Spring Boot, Spring JPA, Liquibase, JUnit4, Swagger, Dockerization

**Database:** PostgresSQL



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

# Installation & Starting

> Packaging
```
./mvnw clean package 
```

>   Start project from source code
```
mvn spring:boot-run
```

Start by docker
```
docker build -t justin-tu:latest .
docker run -d -p 8888:8080 justin-tu:latest
```
## Deployment

To deploy this project run

```bash
  npm run deploy
```

