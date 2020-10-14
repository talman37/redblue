INSERT INTO LAW_FIRMS (ID, NAME, DESCRIPTION, CREATED_AT)
VALUES ('sample-lawfirm-id', '테스트법무사', '테스트 데이터', NOW());

INSERT INTO LAW_FIRM_USERS(ID, LAW_FIRM_ID, EMAIL, PASSWORD, NAME, ROLE, CREATED_AT)
VALUES ('sample-user-id', 'sample-lawfirm-id', 'jhm@mz.co.kr',
        '$2a$10$HkHyiaLtWg98uxAnmweW7uASsdledh6BjtIA51sl4DUh4eUEqVwKe', 'jhm', 'ADMIN', NOW());

INSERT INTO REGISTER_OFFICES(ID, CODE, LOCATION, NAME)
VALUES ('register-office-1', '1101', '서울', '서울중앙지방법원 등기국'),
       ('register-office-2', '1103', '서울', '서울중앙지방법원 중부등기소'),
       ('register-office-3', '2401', '서울', '서울동부지방법원 등기국'),
       ('register-office-4', '2501', '서울', '서울남부지방법원 등기국'),
       ('register-office-5', '2602', '서울', '서울북부지방법원 북부등기소'),
       ('register-office-6', '2641', '서울', '서울북부지방법원 동대문등기소'),
       ('register-office-7', '2643', '서울', '서울북부지방법원 도봉등기소'),
       ('register-office-8', '2701', '서울', '서울서부지방법원 등기과'),
       ('register-office-9', '2741', '서울', '서울서부지방법원 서대문등기소'),
       ('register-office-10', '2742', '서울', '서울서부지방법원 은평등기소'),
       ('register-office-11', '2743', '서울', '서울서부지방법원 용산등기소'),
       ('register-office-12', '2802', '의정부', '의정부지방법원 의정부등기소'),
       ('register-office-13', '2841', '의정부', '의정부지법원 남양주등기소'),
       ('register-office-14', '2842', '의정부', '의정부지방법원 구리등기소'),
       ('register-office-15', '2843', '의정부', '의정부지방법원 연천등기소'),
       ('register-office-16', '2844', '의정부', '의정부지방법원 포천등기소'),
       ('register-office-17', '2845', '의정부', '의정부지방법원 가평등기소'),
       ('register-office-18', '2846', '의정부', '의정부지방법원 동두천등기소'),
       ('register-office-19', '2847', '의정부', '의정부지방법원 철원등기소'),
       ('register-office-20', '2849', '의정부', '의정부방법원 파주등기소'),
       ('register-office-21', '2850', '의정부', '의정부지방법원 고양등기소'),
       ('register-office-22', '1201', '인천', '인천지방법원 등기국'),
       ('register-office-23', '1211', '인천', '인천지방법원 부천지원'),
       ('register-office-24', '1244', '인천', '인천지방법원 김포등기소'),
       ('register-office-25', '1245', '인천', '인천지방법원 강화등기소'),
       ('register-office-26', '1311', '수원', '수원지방법원 성남지원'),
       ('register-office-27', '1312', '수원', '수원지방법원 여주지원'),
       ('register-office-28', '1313', '수원', '수원지방법원 평택지원'),
       ('register-office-29', '1314', '수원', '수원지방법원 안산지원'),
       ('register-office-30', '1341', '수원', '수원지방법원 안양등기소'),
       ('register-office-31', '1342', '수원', '수원지방법원 광주등기소'),
       ('register-office-32', '1343', '수원', '수원지방법원 양평등기소'),
       ('register-office-33', '1344', '수원', '수원지방법원 이천등기소'),
       ('register-office-34', '1345', '수원', '수원지방법원 용인등기소'),
       ('register-office-35', '1346', '수원', '수원지방법원 안성등기소'),
       ('register-office-36', '1348', '수원', '수원지방법원 화성등기소'),
       ('register-office-37', '1349', '수원', '수원지방법원 광명등기소'),
       ('register-office-38', '1546', '청주', '청주지방법원 단양등기소'),
       ('register-office-39', '1354', '수원', '수원지방법원 송탄등기소'),
       ('register-office-40', '1355', '수원', '수원지방법원 시흥등기소'),
       ('register-office-41', '1356', '수원', '수원지방법원 분당등기소'),
       ('register-office-42', '1357', '수원', '수원지방법원 하남등기소'),
       ('register-office-43', '1358', '수원', '수원지방법원 동수원등기 소'),
       ('register-office-44', '1359', '수원', '수원지방법원 장안등기소'),
       ('register-office-45', '1401', '춘천', '춘천지방법원 등기과'),
       ('register-office-46', '1411', '춘천', '춘천지방법원 강릉지원'),
       ('register-office-47', '1412', '춘천', '춘천지방법원 원주지원'),
       ('register-office-48', '1413', '춘천', '춘천지방법원 속초지원'),
       ('register-office-49', '1414', '춘천', '춘천지방법원 영월지원'),
       ('register-office-50', '1441', '춘천', '춘천지방법원 화천등기소'),
       ('register-office-51', '1442', '춘천', '춘천지방법원 양구등기소'),
       ('register-office-52', '1443', '춘천', '춘천지방법원 인제등기소'),
       ('register-office-53', '1601', '대전', '대전지방법원 등기과'),
       ('register-office-54', '1611', '대전', '대전지방법원 흥성지원'),
       ('register-office-55', '1612', '대전', '대전지방법원 공주지 원'),
       ('register-office-56', '1613', '대전', '대전지방법원 논산지 원'),
       ('register-office-57', '1614', '대전', '대전지방법원 서산지원'),
       ('register-office-58', '1615', '대전', '대전지방법원 천안지원'),
       ('register-office-59', '1641', '대전', '대전지방법원 대덕등기소'),
       ('register-office-60', '1642', '대전', '대전지방법원 금산등기소'),
       ('register-office-61', '1643', '대전', '대전지방법원 부여등기소'),
       ('register-office-62', '1644', '대전', '대전지방법원'),
       ('register-office-63', '1645', '대전', '대전지방법원 보령 등기소'),
       ('register-office-64', '1646', '대전', '대전지방법원 청양등기소'),
       ('register-office-65', '1444', '춘천', '춘천지방법원 고성 등기소'),
       ('register-office-66', '1445', '춘천', '춘천지방법원 양양등기소'),
       ('register-office-67', '1446', '춘천', '춘천지방법원 삼척 등기소'),
       ('register-office-68', '1447', '춘천', '춘천지방법원 동해등기소'),
       ('register-office-69', '1448', '춘천', '춘천지방법원 태백등기소'),
       ('register-office-70', '1449', '춘천', '춘천지방법원 정선등기소'),
       ('register-office-71', '1450', '춘천', '춘천지방법원 평창등기소'),
       ('register-office-72', '1451', '춘천', '춘천지방법원 횡성등기소'),
       ('register-office-73', '1452', '춘천', '춘천지방법원 흥천등기소'),
       ('register-office-74', '1501', '청주', '청주지방법원 등기과'),
       ('register-office-75', '1511', '청주', '청주지방법원 충주지 원'),
       ('register-office-76', '1512', '청주', '청주지방법원 제천지원'),
       ('register-office-77', '1513', '청주', '청주지방법원 영동지원'),
       ('register-office-78', '1541', '청주', '청주지방법원 보은등기소'),
       ('register-office-79', '1542', '청주', '청주지방법원 옥천등기소'),
       ('register-office-80', '1543', '청주', '청주지방법원 진천등기소'),
       ('register-office-81', '1544', '청주', '청주지방법원 괴산등기소'),
       ('register-office-82', '1545', '청주', '청주지방법원 음성 등기소'),
       ('register-office-83', '1749', '대구', '대구지방법원 청도등기소'),
       ('register-office-84', '1750', '대구', '대구지방법원 고령등기소'),
       ('register-office-85', '1751', '대구', '대구지방법원 성주등기소'),
       ('register-office-86', '1752', '대구', '대구지방법원 칠곡등기소'),
       ('register-office-87', '1754', '대구', '대구지방법원 문경등기소'),
       ('register-office-88', '1755', '대구', '대구지방법원 예천등기소'),
       ('register-office-89', '1756', '대구', '대구지방법원 영주등기소'),
       ('register-office-90', '1757', '대구', '대구지방법원 봉화등기소'),
       ('register-office-91', '1758', '대구', '대구지방법원 울릉등기소'),
       ('register-office-92', '1759', '대구', '대구지방법원 울진등기소'),
       ('register-office-93', '1760', '대구', '대구지방법원 구미 등기소'),
       ('register-office-94', '1801', '부산', '부산지방법원 등기과'),
       ('register-office-95', '1811', '부산', '부산지방법원 동부지원'),
       ('register-office-96', '1849', '부산', '부산지방법원 서부지원'),
       ('register-office-97', '1841', '부산', '부산지방법원 부산진등기소'),
       ('register-office-98', '1843', '부산', '부산지방법원 남부산등기소'),
       ('register-office-99', '1844', '부산', '부산지방법원 북부산등기소'),
       ('register-office-100', '1847', '부산', '부산지방법원 금정 등기소'),
       ('register-office-101', '1851', '부산', '부산지방법원 중부산등기소'),
       ('register-office-102', '1647', '대전', '대전지방법원 세종등기소'),
       ('register-office-103', '1648', '대전', '대전지방법원 아산등기소'),
       ('register-office-104', '1649', '대전', '대전지방법원 예산등기소'),
       ('register-office-105', '1650', '대전', '대전지방법원 당진등기소'),
       ('register-office-106', '1651', '대전', '대전지방법원 태안등기소'),
       ('register-office-107', '1652', '대전', '대전지방법원 남대전등기소'),
       ('register-office-108', '1701', '대구', '대구지방법원 등기 국'),
       ('register-office-109', '1711', '대구', '대구지방법원 안동지원'),
       ('register-office-110', '1712', '대구', '대구지방법원 경주지원'),
       ('register-office-111', '1713', '대구', '대구지방법원 김천지원'),
       ('register-office-112', '1714', '대구', '대구지방법원 상주지 원'),
       ('register-office-113', '1715', '대구', '대구지방법원 의성지원'),
       ('register-office-114', '1716', '대구', '대구지방법원 영덕지원'),
       ('register-office-115', '1717', '대구', '대구지방법원 포항지원'),
       ('register-office-116', '1718', '대구', '대구지방법원 서부지원'),
       ('register-office-117', '1743', '대구', '대구지방법원 군위 등기소'),
       ('register-office-118', '1744', '대구', '대구지방법원 청송등기소'),
       ('register-office-119', '1745', '대구', '대구지방법원 영양등기소'),
       ('register-office-120', '1747', '대구', '대구지방법원 영천등기소'),
       ('register-office-121', '1748', '대구', '대구지방법원 경산등기소'),
       ('register-office-122', '1945', '창원', '창원지방법원 의령등기소'),
       ('register-office-123', '1946', '창원', '창원지방법원 남해 등기소'),
       ('register-office-124', '1947', '창원', '창원지방법원 하동등기소'),
       ('register-office-125', '1948', '창원', '창원지방법원 산청 등기소'),
       ('register-office-126', '1949', '창원', '창원지방법원 거제등기소'),
       ('register-office-127', '1950', '창원', '창원지방법원 고성등기소'),
       ('register-office-128', '1951', '창원', '창원지방법원 창녕등기소'),
       ('register-office-129', '1952', '창원', '창원지방법원 함양등기소'),
       ('register-office-130', '1953', '창원', '창원지방법원 합천등기소'),
       ('register-office-131', '1954', '창원', '창원지방법원 사천등기소'),
       ('register-office-132', '1955', '창원', '창원지방법원 김해등기소'),
       ('register-office-133', '2001', '광주', '광주지방법원 등기국'),
       ('register-office-134', '2011', '광주', '광주지방법원 목포지원'),
       ('register-office-135', '2012', '광주', '광주지방법원 장흥지원'),
       ('register-office-136', '2013', '광주', '광주지방법원 순천지원'),
       ('register-office-137', '2014', '광주', '광주지방법원 해남지원'),
       ('register-office-138', '2043', '광주', '광주지방법원 담양등기소'),
       ('register-office-139', '2044', '광주', '광주지방법원 곡성 등기소'),
       ('register-office-140', '2045', '광주', '광주지방법원 구례등기소'),
       ('register-office-141', '2301', '울산', '울산지방법원 등기과'),
       ('register-office-142', '2341', '울산', '울산지방법원 양산등기소'),
       ('register-office-143', '1942', '창원', '창원지방법원 등기과'),
       ('register-office-144', '1915', '창원', '창원지방법원 마산지원'),
       ('register-office-145', '1911', '창원', '창원지방법원 진주지원'),
       ('register-office-146', '1912', '창원', '창원지방법원 통영지원'),
       ('register-office-147', '1913', '창원', '창원지방법원 밀양지원'),
       ('register-office-148', '1914', '창원', '창원지방법원 거창지원'),
       ('register-office-149', '1941', '창원', '창원지방법원 진해등기소'),
       ('register-office-150', '1943', '창원', '창원지방법원 함안등기소'),
       ('register-office-151', '1944', '창원', '창원지방법원 삼천포등기소'),
       ('register-office-152', '2046', '광주', '광주지방법원 광양등기소'),
       ('register-office-153', '2047', '광주', '광주지방법원 여수등기소'),
       ('register-office-154', '2049', '광주', '광주지방법원 고흥등기소'),
       ('register-office-155', '2050', '광주', '광주지방법원 보성등기소'),
       ('register-office-156', '2052', '광주', '광주지방법원 화순등기소'),
       ('register-office-157', '2053', '광주', '광주지방법원 강진등기소'),
       ('register-office-158', '2054', '광주', '광주지방법원 영암등기소'),
       ('register-office-159', '2055', '광주', '광주지방법원 나주등기소'),
       ('register-office-160', '2056', '광주', '광주지방법원 함평등기소'),
       ('register-office-161', '2057', '광주', '광주지방법원 무안등기소'),
       ('register-office-162', '2058', '광주', '광주지방법원 영광등기소'),
       ('register-office-163', '2059', '광주', '광주지방법원 장성등기소'),
       ('register-office-164', '2060', '광주', '광주지방법원 완도등기소'),
       ('register-office-165', '2061', '광주', '광주지방법원 진도등기소'),
       ('register-office-166', '2062', '광주', '광주지방법원 여천등기소'),
       ('register-office-167', '2101', '전주', '전주지방법원 전주등기소'),
       ('register-office-168', '2111', '전주', '전주지방법원 군산지원'),
       ('register-office-169', '2112', '전주', '전주지방법원 정읍지원'),
       ('register-office-170', '2113', '전주', '전주지방법원 남원지원'),
       ('register-office-171', '2141', '전주', '전주지방법원 진안등기소'),
       ('register-office-172', '2142', '전주', '전주지방법원 무주등기소'),
       ('register-office-173', '2143', '전주', '전주지방법원 장수등기소'),
       ('register-office-174', '2144', '전주', '전주지방법원 임실등기소'),
       ('register-office-175', '2145', '전주', '전주지방법원 순창등기소'),
       ('register-office-176', '2146', '전주', '전주지방법원 고창등기소'),
       ('register-office-177', '2147', '전주', '전주지방법원 부안등기소'),
       ('register-office-178', '2148', '전주', '전주지방법원 김제등기소'),
       ('register-office-179', '2149', '전주', '전주지방법원 익산등기소'),
       ('register-office-180', '2201', '제주', '제주지방법원 등기과'),
       ('register-office-181', '2241', '제주', '제주지방법원 서귀포등기소');

INSERT INTO LAW_FIRM_USER_REGISTER_OFFICES(USER_ID, REGISTER_OFFICE_ID, NAME)
VALUES ('sample-user-id', 'register-office-1', '(1101)서울중앙지방법원 등기국'),
       ('sample-user-id', 'register-office-23', '(1211)인천지방법원 부천지원'),
       ('sample-user-id', 'register-office-160', '(2056)광주지방법원 함평등기소');

INSERT INTO COMPANIES (ID, LAW_FIRM_ID, BASE_COMPANY_ID, REGISTER_OFFICE, REGISTER_NUMBER, COMPANY_NUMBER1,
                       COMPANY_NUMBER2, COMPANY_NAME, COMPANY_DIVISION, COMPANY_MANAGE_NUMBER, COMPANY_MANAGE_STATE,
                       COMPANY_STATE, DISPLAY_COMPANY_TYPE, COMPANY_SUB_NAME,
                       COMPANY_UPDATED_AT,
                       COMPANY_ADDRESS, COMPANY_POSTAL_CODE, COMPANY_ADDRESS_UPDATED_AT,
                       BUSINESS_NUMBER, BUSINESS_TYPE, BUSINESS_CONDITION, DELIVERY_PLACE, DELIVERY_PLACE_POSTAL_CODE,
                       NOTICE_WAY, NOTICE_WAY_UPDATED_AT, ETC,
                       CONVERTIBLE_BOND, STOCK_PURCHASE_OPTION, COMPANY_FORMATION_AT,
                       REGISTER_RECORD_CREATE_REASON, REGISTER_RECORD_CREATE_AT, IS_HEAD_OFFICE_TRANSFER,
                       HEAD_OFFICE_TRANSFER_AT, IS_DISBAND, DISBAND_AT,
                       DISBAND_DEEMED_AT, IS_LIQUIDATION, LIQUIDATION_AT,
                       IS_REGISTER_RECORD_CLOSURE, REGISTER_RECORD_CLOSURE_AT,
                       SETTLEMENT_MONTH, RECOMMENDER)
VALUES ('sample-company-id-01', 'sample-lawfirm-id', null, '고양', 41429, '285011', '0414297', '온빛전자', '유한회사', null, null,
        null, 'FRONT',
        '온빛전자1',
        NULL,
        '경기도 고양시 일산동구 고봉로620번길 81-17, 가,나동(성석동)', '12345', NULL, '2221133333', 'LED 전광판', '제조,도소매',
        '경기도 고양시 일산동구 고봉로620번길 81-17, 가,나동(성석동)', '12345', NULL, NULL, '본지점 이전/폐지
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
        회사는 임직원에게 상법 제340조의2의 규정에 의한 주식매수선택권을 부여할 수 있다.', '2019-06-13', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL,
        NULL,
        NULL, NULL, NULL, 6, null);

INSERT INTO STOCKS (ID, COMPANY_ID, AMOUNT, AMOUNT_UPDATED_AT,
                    SCHEDULE_COUNT, SCHEDULE_COUNT_UPDATED_AT,
                    ISSUED_COUNT, ISSUED_COUNT_UPDATED_AT,
                    NORMAL_COUNT, FIRST_COUNT, CAPITAL, NO_FACE_VALUE_COUNT, NO_FACE_VALUE_UPDATED_AT,
                    NO_FACE_VALUE_CAPITAL_AMOUNT,
                    NO_FACE_VALUE_CAPITAL_AMOUNT_UPDATED_AT)
VALUES ('sample-stock-id-01', 'sample-company-id-01', 5000, '2016-10-20', 1000000, '2016-03-15',
        23600, '2019-12-13', 23600, 0, 0, NULL, NULL, NULL,
        NULL);


INSERT INTO EXECUTIVES (ID, COMPANY_ID, DETAIL, TYPE, NAME, REGISTRATION_NUMBER1, REGISTRATION_NUMBER2, ADDRESS,
                        POSITION, INAUGURATED_AT, TERM, UPDATED_REASON, UPDATED_AT, EXPIRED_AT, STOCK_COUNT)
VALUES ('sample-executive-id-01', 'sample-company-id-01', '공동대표', '이사', '오희택', '810211', '1081221',
        '경기도 용인시 수지구 성복2로 126, 306동 1902호(성복동, 성동마을엘지빌리지3차)', '사내이사', null, 2, '중임', '2019-03-15', '2022-03-15',
        100),
       ('sample-executive-id-02', 'sample-company-id-01', null, '감사', '이상수', '780628', '1005513',
        '서울특별시 도봉구 해등로 168(쌍문동)', '감사', null, 2, '취임', '2019-03-15', '2022-03-15', 200),
       ('sample-executive-id-03', 'sample-company-id-01', null, '청산인', '고성원', '840218', '1106221',
        '울산광역시 중구 평산로 50, 111동 1004호(약사동, 약사아이파크)', '청산인', null, 2, '취임', '2019-03-15', '2022-03-15', 10000);

INSERT INTO STOCKHOLDERS (ID, COMPANY_ID, NAME, REGISTRATION_NUMBER1, REGISTRATION_NUMBER2, ADDRESS, STOCK_COUNT)
VALUES ('sample-stockholder-id-01', 'sample-company-id-01', '오희택', '810211', '1081221',
        '경기도 용인시 수지구 성복2로 126, 306동 1902호(성복동, 성동마을엘지빌리지3차)', 100),
       ('sample-stockholder-id-02', 'sample-company-id-01', '이상수', '780628', '1005513', '서울특별시 도봉구 해등로 168(쌍문동)', 200),
       ('sample-stockholder-id-03', 'sample-company-id-01', '고성원', '840218', '1106221',
        '울산광역시 중구 평산로 50, 111동 1004호(약사동, 약사아이파크)', 10000);

INSERT INTO CONSULTS (ID, LAW_FIRM_ID, COMPANY_ID, CONSULTANT, CONTENT, MEMO, PROGRESS, CREATED_AT, UPDATED_AT)
VALUES ('sample-consult-id-01', 'sample-lawfirm-id', 'sample-company-id-01', '정하명', '상당내용1', '상담 메모1', 'ONGOING',
        '2019-06-13', null),
       ('sample-consult-id-02', 'sample-lawfirm-id', 'sample-company-id-01', '정하명', '상당내용2', '상담 메모2', 'COMPLETE',
        '2019-06-13', null),
       ('sample-consult-id-03', 'sample-lawfirm-id', 'sample-company-id-01', '정하명', '상당내용3', '상담 메모3', 'CANCEL',
        '2019-06-13', null),
       ('sample-consult-id-04', 'sample-lawfirm-id', 'sample-company-id-01', '정하명', '상당내용4', '상담 메모4', 'HOLD',
        '2019-06-13', null);