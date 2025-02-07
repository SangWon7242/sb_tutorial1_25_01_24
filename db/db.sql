# DB 삭제, 생성, 수정
DROP DATABASE IF EXISTS tutorial1__dev;
CREATE DATABASE tutorial1__dev;
USE tutorial1__dev;

# 테스트 회원 생성
INSERT INTO `member`
SET create_date = NOW(),
modify_date = NOW(),
username = 'user1',
`password` = '1234';