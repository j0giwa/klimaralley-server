# klimaralley-server

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Testing](#testing)

## Introduction

Backendserver for the Lemgo Klimaralley project (prototype)

## Features

fill out later

## Installation

Clone the repository:
```bash
git clone https://github.com/j0giwa/klimaralley-server.git
cd klimaralley-server
```

## Usage
Run the server in development mode:
```bash
mvn spring-boot:run
```

Run the server in production mode:
```bash
mvn package -DskipTests
java -jar target/klimaralley.server-0.0.1-SNAPSHOT.jar
```

## API Documentation
The API documentation is generated using [Swagger](https://swagger.io). 
To view the documentation, start the server and navigate to [Swagger UI (http://localhost:8080/docs)](http://localhost:8080/docs).

## Testing
Testing is automated via github actions.
Run tests manualy:
```bash
mvn test
```

