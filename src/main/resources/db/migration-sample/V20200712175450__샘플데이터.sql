INSERT INTO LAW_FIRMS (ID, NAME, DESCRIPTION, CREATED_AT)
VALUES ('sample-lawfirm-id', '테스트법무사', '테스트 데이터', NOW());

INSERT INTO LAW_FIRM_USERS(ID, LAW_FIRM_ID, EMAIL, PASSWORD, NAME, ROLE, CREATED_AT)
VALUES ('sample-user-id', 'sample-lawfirm-id', 'jhm@mz.co.kr',
        '$2a$10$HkHyiaLtWg98uxAnmweW7uASsdledh6BjtIA51sl4DUh4eUEqVwKe', 'jhm', 'ADMIN', NOW());

INSERT INTO COMPANIES (ID, LAW_FIRM_ID, BASE_COMPANY_ID, REGISTER_OFFICE, REGISTER_NUMBER, COMPANY_NUMBER1,
                       COMPANY_NUMBER2, COMPANY_NAME, DISPLAY_COMPANY_TYPE, COMPANY_SUB_NAME, COMPANY_UPDATED_AT,
                       COMPANY_REGISTER_UPDATED_AT,
                       COMPANY_ADDRESS, COMPANY_POSTAL_CODE, COMPANY_ADDRESS_UPDATED_AT,
                       COMPANY_ADDRESS_REGISTER_UPDATED_AT,
                       BUSINESS_NUMBER, BUSINESS_TYPE, BUSINESS_CONDITION, DELIVERY_PLACE, DELIVERY_PLACE_POSTAL_CODE,
                       ETC,
                       CONVERTIBLE_BOND, STOCK_PURCHASE_OPTION, COMPANY_FORMATION_AT,
                       REGISTER_RECORD_CREATE_REASON, REGISTER_RECORD_CREATE_AT, IS_HEAD_OFFICE_TRANSFER,
                       HEAD_OFFICE_TRANSFER_AT, HEAD_OFFICE_TRANSFER_REGISTER_AT, IS_DISBAND, DISBAND_AT,
                       DISBAND_REGISTER_AT, DISBAND_DEEMED_AT, IS_LIQUIDATION, LIQUIDATION_AT,
                       LIQUIDATION_REGISTER_AT, IS_REGISTER_RECORD_CLOSURE, REGISTER_RECORD_CLOSURE_AT,
                       SETTLEMENT_MONTH)
VALUES ('sample-company-id-01', 'sample-lawfirm-id', null, '고양', 41429, '285011', '0414297', '온빛전자', 'FRONT', '온빛전자1',
        NULL, NULL,
        '경기도 고양시 일산동구 고봉로620번길 81-17, 가,나동(성석동)', '12345', NULL, NULL, '2221133333', 'LED 전광판', '제조,도소매',
        '경기도 고양시 일산동구 고봉로620번길 81-17, 가,나동(성석동)', '12345', '본지점 이전/폐지
자본에 관한 사항
합병/조직변경
분할/분할합병
파산 및 화의
해산/청산
회사정리
회생절차
등기기록 부활 및 폐쇄
담보부사채신탁
기타
무효판결
취소',
        '제1호 전환사채
        1. 전환사채의 총액금1,000,000원',
        '주식매수선택권
        1. 일정한 경우 주식매수택권을 부여할수 있다는 뜻
        회사는 임직원에게 상법 제340조의2의 규정에 의한 주식매수선택권을 부여할 수 있다.', '2019-06-13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL,
        NULL,
        NULL, NULL, NULL, NULL, 6);

INSERT INTO STOCKS (ID, COMPANY_ID, AMOUNT, AMOUNT_UPDATED_AT, AMOUNT_UPDATED_REGISTER_AT,
                        SCHEDULE_COUNT, SCHEDULE_COUNT_UPDATED_AT, SCHEDULE_COUNT_UPDATED_REGISTER_AT,
                        ISSUED_COUNT, ISSUED_COUNT_UPDATED_AT, ISSUED_COUNT_UPDATED_REGISTER_AT,
                        NORMAL_COUNT, FIRST_COUNT, NO_FACE_VALUE_COUNT, NO_FACE_VALUE_UPDATED_AT,
                        NO_FACE_VALUE_UPDATED_REGISTER_AT, NO_FACE_VALUE_CAPITAL_AMOUNT,
                        NO_FACE_VALUE_CAPITAL_AMOUNT_UPDATED_AT,
                        NO_FACE_VALUE_CAPITAL_AMOUNT_UPDATED_REGISTER_AT)
VALUES ('sample-stock-id-01', 'sample-company-id-01', 5000, '2016-10-20', '2016-10-20', 1000000, '2016-03-15',
        '2016-03-16', 23600, '2019-12-13', '2019-12-19', 23600, 0, NULL, NULL, NULL, NULL, NULL,
        NULL);