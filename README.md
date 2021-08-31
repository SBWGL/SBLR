# Springboot_SM
# 스프링부트 공부겸 자기개발을 위한 호텔 예약 프로젝트(2021/08/23 - 2021/08/31) - 1차(추가 작업 필요)
---
***use SpringBoot + thymeleaf + Spring data JPA + SpringSecurity + OAuth2 + Bootstrap + jQuary***

- 회원 페이지
	- OAuth2-client 인증을 통한 Google, Facebook, Naver, Kakao 로그인 및 회원가입

- 객실 페이지
	- 객실 보기
	- 객실 상세보기
	- 객실 추가
	- 객실 수정
	- 객실 검색
	- 페이징
	- 리뷰

- 결제
	- 아임포트의 카카오페이 결제 서비스
  
- 예약 확인 페이지
	- 예약 정보 보기
  
- 나와 접촉 페이지
	- Google Maps를 통한 지도
	<hr>
- 🆘🆘 ajax의 GET방식으로 가져오기 위해 경로상에 값을 넣어주는데
  이때의 체크인, 체크아웃 날짜 형식이 00/00/0000이므로 '/'가 경로로 포함됨
  따라서 datepicker.js에서  날짜 형식 '-'등 으로 수정 요망
  (또는 post방식으로 받을 시 해당 데이터에 대한 List<Room>을 가져와 어떻게 보여줄지 왜냐하면 
  PostMapping에서 redirect로 경로로 이동해서 해당 경로로 매핑되는 화면을 보여주고자 하지만 다른 화면(rooms)
  가 보여짐)
---
