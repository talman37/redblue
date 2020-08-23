CREATE TABLE USERS
(
    ID          VARCHAR(50)  NOT NULL,
    LAW_FIRM_ID VARCHAR(50)  NOT NULL,
    USERNAME    VARCHAR(45)  NOT NULL,
    PASSWORD    VARCHAR(100) NOT NULL,
    NAME        VARCHAR(100) NOT NULL,
    EMAIL       VARCHAR(100) NULL,
    CREATED_AT  TIMESTAMP    NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE LAW_FIRM
(
    ID          VARCHAR(50) NOT NULL,
    NAME        VARCHAR(45) NOT NULL,
    DESCRIPTION VARCHAR(45) NULL,
    CREATED_AT  TIMESTAMP   NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE COMPANY
(
    ID                                  VARCHAR(50)   NOT NULL,
    LAW_FIRM_ID                         VARCHAR(50)   NOT NULL,
    REGISTER_OFFICE                     VARCHAR(45)   NULL COMMENT '등기소',
    REGISTER_NUMBER                     INT           NULL COMMENT '등기번호',
    COMPANY_NUMBER1                     INT           NULL COMMENT '법인등록번호1',
    COMPANY_NUMBER2                     INT           NULL COMMENT '법인등록번호2',
    COMPANY_NAME                        VARCHAR(45)   NULL COMMENT '상호',
    COMPANY_SUB_NAME                    VARCHAR(45)   NULL COMMENT '병기상호',
    COMPANY_UPDATED_AT                  TIMESTAMP     NULL COMMENT '상호변경일',
    COMPANY_REGISTER_UPDATED_AT         TIMESTAMP     NULL COMMENT '상호변경등기일',
    COMPANY_ADDRESS                     VARCHAR(45)   NULL COMMENT '본점주소',
    COMPANY_ADDRESS_UPDATED_AT          TIMESTAMP     NULL COMMENT '본점주소 변경일',
    COMPANY_ADDRESS_REGISTER_UPDATED_AT TIMESTAMP     NULL COMMENT '본점주소 변경등기일',
    BUSINESS_TYPE                       VARCHAR(45)   NULL COMMENT '업종',
    BUSINESS_CONDITION                  VARCHAR(45)   NULL COMMENT '업태',
    DELIVERY_PLACE                      VARCHAR(45)   NULL COMMENT '송달장소',
    ETC                                 VARCHAR(1000) NULL COMMENT '기타사항',
    CONVERTIBLE_BOND                    VARCHAR(100)  NULL COMMENT '전환사채',
    STOCK_PURCHASE_OPTION               VARCHAR(100)  NULL COMMENT '주식매수선택권',
    COMPANY_FORMATION_AT                TIMESTAMP     NULL COMMENT '회사성립연월일',
    REGISTER_RECORD_CREATE_REASON       VARCHAR(45)   NULL COMMENT '등기기록개설사유',
    REGISTER_RECORD_CREATE_AT           TIMESTAMP     NULL COMMENT '등기기록개설연월일',
    IS_HEAD_OFFICE_TRANSFER             BIT           NULL COMMENT '본점전출여부',
    HEAD_OFFICE_TRANSFER_AT             TIMESTAMP     NULL COMMENT '본점전출연월일',
    HEAD_OFFICE_TRANSFER_REGISTER_AT    TIMESTAMP     NULL COMMENT '본점전출등기일',
    IS_DISBAND                          BIT           NULL COMMENT '해상여부',
    DISBAND_AT                          TIMESTAMP     NULL COMMENT '해산일',
    DISBAND_REGISTER_AT                 TIMESTAMP     NULL COMMENT '해산등기일',
    DISBAND_DEEMED_AT                   TIMESTAMP     NULL COMMENT '해산간주일',
    IS_LIQUIDATION                      BIT           NULL COMMENT '청산여부',
    LIQUIDATION_AT                      TIMESTAMP     NULL COMMENT '청산일',
    LIQUIDATION_REGISTER_AT             TIMESTAMP     NULL COMMENT '청산등기일',
    IS_REGISTER_RECORD_CLOSURE          BIT           NULL COMMENT '등기기록폐쇄여부',
    REGISTER_RECORD_CLOSURE_AT          TIMESTAMP     NULL COMMENT '등기기록폐쇄일',
    SETTLEMENT_AT                       TIMESTAMP     NULL COMMENT '결산기일',
    PRIMARY KEY (ID)
);