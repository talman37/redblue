CREATE TABLE LAW_FIRMS
(
    ID          VARCHAR(50) NOT NULL,
    NAME        VARCHAR(45) NOT NULL,
    DESCRIPTION VARCHAR(45) NULL,
    CREATED_AT  DATE        NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE LAW_FIRM_USERS
(
    ID          VARCHAR(50)  NOT NULL,
    LAW_FIRM_ID VARCHAR(50)  NOT NULL,
    EMAIL       VARCHAR(100) NOT NULL,
    PASSWORD    VARCHAR(100) NOT NULL,
    NAME        VARCHAR(100) NOT NULL,
    ROLE        VARCHAR(50)  NOT NULL,
    CREATED_AT  DATE         NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE COMPANIES
(
    ID                                  VARCHAR(50)   NOT NULL,
    LAW_FIRM_ID                         VARCHAR(50)   NOT NULL,
    BASE_COMPANY_ID                     VARCHAR(50)   NULL COMMENT '원시 법인데이터 아이디',
    REGISTER_OFFICE                     VARCHAR(45)   NULL COMMENT '등기소',
    REGISTER_NUMBER                     INTEGER       NULL COMMENT '등기번호',
    COMPANY_NUMBER1                     INTEGER       NULL COMMENT '법인등록번호1',
    COMPANY_NUMBER2                     INTEGER       NULL COMMENT '법인등록번호2',
    COMPANY_NAME                        VARCHAR(45)   NULL COMMENT '상호',
    COMPANY_DIVISION                    VARCHAR(20)   NULL COMMENT '법인 구분',
    DISPLAY_COMPANY_TYPE                VARCHAR(10)   NULL COMMENT '법인 종류 표시',
    COMPANY_SUB_NAME                    VARCHAR(45)   NULL COMMENT '병기상호',
    COMPANY_UPDATED_AT                  DATE          NULL COMMENT '상호변경일',
    COMPANY_ADDRESS                     VARCHAR(45)   NULL COMMENT '본점주소',
    COMPANY_POSTAL_CODE                 VARCHAR(20)   NULL COMMENT '본점주소 우편번호',
    COMPANY_ADDRESS_UPDATED_AT          DATE          NULL COMMENT '본점주소 변경일',
    BUSINESS_NUMBER                     VARCHAR(30)   NULL COMMENT '사업자등록 번호',
    BUSINESS_TYPE                       VARCHAR(45)   NULL COMMENT '업종',
    BUSINESS_CONDITION                  VARCHAR(45)   NULL COMMENT '업태',
    DELIVERY_PLACE                      VARCHAR(45)   NULL COMMENT '송달장소',
    DELIVERY_PLACE_POSTAL_CODE          VARCHAR(20)   NULL COMMENT '송달장소 우편번호',
    ETC                                 VARCHAR(1000) NULL COMMENT '기타사항',
    CONVERTIBLE_BOND                    VARCHAR(100)  NULL COMMENT '전환사채',
    STOCK_PURCHASE_OPTION               VARCHAR(200)  NULL COMMENT '주식매수선택권',
    COMPANY_FORMATION_AT                DATE          NULL COMMENT '회사성립연월일',
    REGISTER_RECORD_CREATE_REASON       VARCHAR(45)   NULL COMMENT '등기기록개설사유',
    REGISTER_RECORD_CREATE_AT           DATE          NULL COMMENT '등기기록개설연월일',
    IS_HEAD_OFFICE_TRANSFER             BIT           NULL COMMENT '본점전출여부',
    HEAD_OFFICE_TRANSFER_AT             DATE          NULL COMMENT '본점전출연월일',
    IS_DISBAND                          BIT           NULL COMMENT '해상여부',
    DISBAND_AT                          DATE          NULL COMMENT '해산일',
    DISBAND_REGISTER_AT                 DATE          NULL COMMENT '해산등기일',
    DISBAND_DEEMED_AT                   DATE          NULL COMMENT '해산간주일',
    IS_LIQUIDATION                      BIT           NULL COMMENT '청산여부',
    LIQUIDATION_AT                      DATE          NULL COMMENT '청산일',
    IS_REGISTER_RECORD_CLOSURE          BIT           NULL COMMENT '등기기록폐쇄여부',
    REGISTER_RECORD_CLOSURE_AT          DATE          NULL COMMENT '등기기록폐쇄일',
    SETTLEMENT_MONTH                    INTEGER       NULL COMMENT '결산기일',
    PRIMARY KEY (ID)
);

CREATE TABLE STOCKS
(
    ID                                               VARCHAR(50) NOT NULL,
    COMPANY_ID                                       VARCHAR(50) NOT NULL,
    AMOUNT                                           INTEGER     NULL COMMENT '1주의 금액',
    AMOUNT_UPDATED_AT                                DATE        NULL COMMENT '1주의 금액 변경일',
    SCHEDULE_COUNT                                   INTEGER     NULL COMMENT '발행할 주식의 총수',
    SCHEDULE_COUNT_UPDATED_AT                        DATE        NULL COMMENT '발행할 주식의 총수 변경일',
    ISSUED_COUNT                                     INTEGER     NULL COMMENT '발행주식의 총수',
    ISSUED_COUNT_UPDATED_AT                          DATE        NULL COMMENT '발행주식의 총수 변경일',
    NORMAL_COUNT                                     INTEGER     NULL COMMENT '보통 주식수',
    FIRST_COUNT                                      INTEGER     NULL COMMENT '우선 주식수',
    NO_FACE_VALUE_COUNT                              INTEGER     NULL COMMENT '무액면 주식수',
    NO_FACE_VALUE_UPDATED_AT                         DATE        NULL COMMENT '무액면 주식의 변경일',
    NO_FACE_VALUE_CAPITAL_AMOUNT                     INTEGER     NULL COMMENT '무액면 주식의 자본전입액',
    NO_FACE_VALUE_CAPITAL_AMOUNT_UPDATED_AT          DATE        NULL COMMENT '무액면 주식의 자본전입액 변경일',
    PRIMARY KEY (ID)
);

CREATE TABLE PURPOSE_DETAILS
(
    ID                         VARCHAR(50) NOT NULL,
    COMPANY_ID                 VARCHAR(50) NOT NULL,
    DETAIL                     VARCHAR(45) NULL COMMENT '목적사항',
    DETAIL_UPDATED_AT          DATE        NULL COMMENT '목적사항 변경일',
    PRIMARY KEY (ID)
);

CREATE TABLE EXECUTIVES
(
    ID                   VARCHAR(50)  NOT NULL,
    COMPANY_ID           VARCHAR(50)  NOT NULL,
    DETAIL               VARCHAR(45)  NULL COMMENT '임원에 관한 사항',
    TYPE                 VARCHAR(45)  NULL COMMENT '임원의 종류',
    NAME                 VARCHAR(45)  NULL COMMENT '임원 이름',
    REGISTRATION_NUMBER1 VARCHAR(20)  NULL COMMENT '주민등록번호1',
    REGISTRATION_NUMBER2 VARCHAR(20)  NULL COMMENT '주민등록번호2',
    ADDRESS              VARCHAR(200) NULL COMMENT '임원 주소',
    POSITION             VARCHAR(30)  NULL COMMENT '직위',
    UPDATED_REASON       VARCHAR(30)  NULL COMMENT '임원 변경 사유',
    UPDATED_AT           DATE         NULL COMMENT '임원 변경일',
    EXPIRED_AT           DATE         NULL COMMENT '임원 만료일',
    STOCK_COUNT          INTEGER      NULL COMMENT '주식수',
    PRIMARY KEY (ID)
);

CREATE TABLE STOCKHOLDERS
(
    ID                   VARCHAR(50)  NOT NULL,
    COMPANY_ID           VARCHAR(50)  NOT NULL,
    NAME                 VARCHAR(45)  NULL COMMENT '주주 이름',
    REGISTRATION_NUMBER1 VARCHAR(20)  NULL COMMENT '주민등록번호1',
    REGISTRATION_NUMBER2 VARCHAR(20)  NULL COMMENT '주민등록번호2',
    ADDRESS              VARCHAR(200) NULL COMMENT '주주 주소',
    STOCK_COUNT          INTEGER      NULL COMMENT '주식수',
    PRIMARY KEY (ID)
);

CREATE TABLE CONSULTS
(
    ID          VARCHAR(50)   NOT NULL,
    LAW_FIRM_ID VARCHAR(50)   NOT NULL,
    COMPANY_ID  VARCHAR(50)   NOT NULL,
    CONSULTANT  VARCHAR(100)  NOT NULL,
    CONTENT     VARCHAR(1000) NOT NULL,
    MEMO        VARCHAR(1000) NULL,
    PROGRESS    VARCHAR(20)   NOT NULL,
    CREATED_AT  DATE          NULL,
    UPDATED_AT  DATE          NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE BASE_COMPANIES
(
    ID                                  VARCHAR(50)   NOT NULL COMMENT '원시법인 아이디',
    REGISTER_OFFICE                     VARCHAR(45)   NULL COMMENT '등기소',
    REGISTER_NUMBER                     INTEGER       NULL COMMENT '등기번호',
    COMPANY_NUMBER1                     INTEGER       NULL COMMENT '법인등록번호1',
    COMPANY_NUMBER2                     INTEGER       NULL COMMENT '법인등록번호2',
    COMPANY_NAME                        VARCHAR(45)   NULL COMMENT '상호',
    COMPANY_DIVISION                    VARCHAR(20)   NULL COMMENT '법인 구분',
    DISPLAY_COMPANY_TYPE                VARCHAR(10)   NULL COMMENT '법인 종류 표시',
    COMPANY_SUB_NAME                    VARCHAR(45)   NULL COMMENT '병기상호',
    COMPANY_UPDATED_AT                  DATE          NULL COMMENT '상호변경일',
    COMPANY_ADDRESS                     VARCHAR(45)   NULL COMMENT '본점주소',
    COMPANY_POSTAL_CODE                 VARCHAR(20)   NULL COMMENT '본점주소 우편번호',
    COMPANY_ADDRESS_UPDATED_AT          DATE          NULL COMMENT '본점주소 변경일',
    BUSINESS_NUMBER                     VARCHAR(30)   NULL COMMENT '사업자등록 번호',
    BUSINESS_TYPE                       VARCHAR(45)   NULL COMMENT '업종',
    BUSINESS_CONDITION                  VARCHAR(45)   NULL COMMENT '업태',
    DELIVERY_PLACE                      VARCHAR(45)   NULL COMMENT '송달장소',
    DELIVERY_PLACE_POSTAL_CODE          VARCHAR(20)   NULL COMMENT '송달장소 우편번호',
    ETC                                 VARCHAR(1000) NULL COMMENT '기타사항',
    CONVERTIBLE_BOND                    VARCHAR(100)  NULL COMMENT '전환사채',
    STOCK_PURCHASE_OPTION               VARCHAR(200)  NULL COMMENT '주식매수선택권',
    COMPANY_FORMATION_AT                DATE          NULL COMMENT '회사성립연월일',
    REGISTER_RECORD_CREATE_REASON       VARCHAR(45)   NULL COMMENT '등기기록개설사유',
    REGISTER_RECORD_CREATE_AT           DATE          NULL COMMENT '등기기록개설연월일',
    IS_HEAD_OFFICE_TRANSFER             BIT           NULL COMMENT '본점전출여부',
    HEAD_OFFICE_TRANSFER_AT             DATE          NULL COMMENT '본점전출연월일',
    IS_DISBAND                          BIT           NULL COMMENT '해상여부',
    DISBAND_AT                          DATE          NULL COMMENT '해산일',
    DISBAND_REGISTER_AT                 DATE          NULL COMMENT '해산등기일',
    DISBAND_DEEMED_AT                   DATE          NULL COMMENT '해산간주일',
    IS_LIQUIDATION                      BIT           NULL COMMENT '청산여부',
    LIQUIDATION_AT                      DATE          NULL COMMENT '청산일',
    IS_REGISTER_RECORD_CLOSURE          BIT           NULL COMMENT '등기기록폐쇄여부',
    REGISTER_RECORD_CLOSURE_AT          DATE          NULL COMMENT '등기기록폐쇄일',
    SETTLEMENT_MONTH                    INTEGER       NULL COMMENT '결산기일',
    PRIMARY KEY (ID)
);

CREATE TABLE BASE_COMPANY_STOCKS
(
    ID                                               VARCHAR(50) NOT NULL,
    BASE_COMPANY_COMPANY_ID                          VARCHAR(50) NOT NULL,
    AMOUNT                                           INTEGER     NULL COMMENT '1주의 금액',
    AMOUNT_UPDATED_AT                                DATE        NULL COMMENT '1주의 금액 변경일',
    SCHEDULE_COUNT                                   INTEGER     NULL COMMENT '발행할 주식의 총수',
    SCHEDULE_COUNT_UPDATED_AT                        DATE        NULL COMMENT '발행할 주식의 총수 변경일',
    ISSUED_COUNT                                     INTEGER     NULL COMMENT '발행주식의 총수',
    ISSUED_COUNT_UPDATED_AT                          DATE        NULL COMMENT '발행주식의 총수 변경일',
    NORMAL_COUNT                                     INTEGER     NULL COMMENT '보통 주식수',
    FIRST_COUNT                                      INTEGER     NULL COMMENT '우선 주식수',
    NO_FACE_VALUE_COUNT                              INTEGER     NULL COMMENT '무액면 주식수',
    NO_FACE_VALUE_UPDATED_AT                         DATE        NULL COMMENT '무액면 주식의 변경일',
    NO_FACE_VALUE_CAPITAL_AMOUNT                     INTEGER     NULL COMMENT '무액면 주식의 자본전입액',
    NO_FACE_VALUE_CAPITAL_AMOUNT_UPDATED_AT          DATE        NULL COMMENT '무액면 주식의 자본전입액 변경일',
    PRIMARY KEY (ID)
);

CREATE TABLE BASE_COMPANY_PURPOSE_DETAILS
(
    ID                         VARCHAR(50) NOT NULL,
    BASE_COMPANY_COMPANY_ID    VARCHAR(50) NOT NULL,
    DETAIL                     VARCHAR(45) NULL COMMENT '목적사항',
    DETAIL_UPDATED_AT          DATE        NULL COMMENT '목적사항 변경일',
    PRIMARY KEY (ID)
);

CREATE TABLE BASE_COMPANY_EXECUTIVES
(
    ID                      VARCHAR(50) NOT NULL,
    BASE_COMPANY_COMPANY_ID VARCHAR(50) NOT NULL,
    DETAIL                  VARCHAR(45) NULL COMMENT '임원에 관한 사항',
    TYPE                    VARCHAR(45) NULL COMMENT '임원의 종류',
    NAME                    VARCHAR(45) NULL COMMENT '임원 이름',
    REGISTRATION_NUMBER1    VARCHAR(45) NULL COMMENT '주민등록번호1',
    REGISTRATION_NUMBER2    VARCHAR(45) NULL COMMENT '주민등록번호2',
    ADDRESS                 VARCHAR(45) NULL COMMENT '임원 주소',
    POSITION                VARCHAR(45) NULL COMMENT '직위',
    UPDATED_REASON          VARCHAR(45) NULL COMMENT '임원 변경 사유',
    UPDATED_AT              DATE        NULL COMMENT '임원 변경일',
    EXPIRED_AT              DATE        NULL COMMENT '임원 만료일',
    STOCK_COUNT             INTEGER     NULL COMMENT '주식수',
    PRIMARY KEY (ID)
);

CREATE TABLE BASE_STOCKHOLDERS
(
    ID                      VARCHAR(50)  NOT NULL,
    BASE_COMPANY_COMPANY_ID VARCHAR(50)  NOT NULL,
    NAME                    VARCHAR(45)  NULL COMMENT '주주 이름',
    REGISTRATION_NUMBER1    VARCHAR(20)  NULL COMMENT '주민등록번호1',
    REGISTRATION_NUMBER2    VARCHAR(20)  NULL COMMENT '주민등록번호2',
    ADDRESS                 VARCHAR(200) NULL COMMENT '주주 주소',
    STOCK_COUNT             INTEGER      NULL COMMENT '주식수',
    PRIMARY KEY (ID)
);