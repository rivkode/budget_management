# 예산 관리 어플리케이션

<br/>

## Table of Contents

- [개요](#개요)
- [Skils](#skils)
- [Installation](#Installation)
- [API Reference](#api-reference)
- [프로젝트 진행 및 이슈 관리](#프로젝트-진행-및-이슈-관리)
- [구현과정(설계 및 의도)](<#구현과정(설계-및-의도)>)
- [TIL 및 회고](#til-및-회고)
- [Authors](#authors)
- [References](#references)

<br/>

## 개요

본 서비스는 사용자들이 개인 재무를 관리하고 지출을 추적하는 데 도움을 주는 애플리케이션입니다. 이 앱은 사용자들이 예산을 설정하고 지출을 모니터링하며 재무 목표를 달성하는 데 도움이 됩니다.

**(지출을 추적한 결과를 토대로 지출 통계 기능을 지원합니다)**


## Skils

<div align="center">

언어 및 프레임워크 <br/> ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) ![SPRING](https://img.shields.io/badge/spring-6DA55F?style=for-the-badge&logo=spring&logoColor=white) ![JAVA](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white)
<br/>
데이터 베이스 <br/>![Mysql](https://img.shields.io/badge/mysql-%23316192.svg?style=for-the-badge&logo=mysql&logoColor=white)<br/>

</div>

## Installation


```bash
  # 설치
  git clone .git
  
  # 실행
  ./gradlew build -x test
  
  # 파일위치로 이동 후
  javac .java
```

## Directory

<details>
<summary> 파일 구조 보기 </summary>

```
src
├─common
│  ├─config
│  ├─dto
│  ├─entity
│  ├─error
│  └─exception
├─user
│  ├─controller
│  ├─dto
│  ├─entity
│  ├─repository
│  └─service
├─budget
│  ├─controller
│  ├─dto
│  ├─entity
│  ├─repository
│  └─service
├─expense
│  ├─controller
│  ├─dto
│  ├─entity
│  ├─repository
│  └─service
└─util
    └─CustomResponseUtil
```

</details>
<br/>

## API Reference

Swagger : http://localhost:{port}/swagger#/

<details>

<summary>Get all posts - click</summary>
<img src="./public/full.png" alt="logo" width="80%" />
<img src="./public/members_get.png" alt="logo" width="80%" />
<img src="./public/members_post.png" alt="logo" width="80%" />
<img src="./public/members_put.png" alt="logo" width="80%" />
<img src="./public/reviews_post.png" alt="logo" width="80%" />
</details>

<br/>

## 프로젝트 진행 및 이슈 관리

[//]: # ([![Notion]&#40;https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white&#41;]&#40;https://www.notion.so/Team-Careerly-8d62334735154f7f9b9cbba91da21df5&#41;)

[//]: # ([프로젝트 관리 페이지]&#40;https://www.notion.so/Team-Careerly-8d62334735154f7f9b9cbba91da21df5&#41;)

<img src="./public/timeline.png" alt="logo" width="80%" />

<br/>

## 구현과정(설계 및 의도)

ERD

<img src="./public/erd.png" alt="logo" width="80%" />

[budget_management_erd](https://lucid.app/lucidchart/7e0d6cd7-f045-4cf3-a526-aa78cac905e9/edit?invitationId=inv_ad8e0887-5199-422a-9883-4de20341c21e&page=0_0#)

<details>
<summary>entity 설계 시 고려사항- click</summary>

- 주요 도메인으로 user, expense, budget, category 으로 나눈다
- 요구사항으로 JWT를 통해 유효성을 검증한다는 내용이 있음
    - 이에 대해 JWT와 세션을 함께 구현할까 고민하였지만 우선순위가 아니므로 추후 시간이 남으면 개발
- Expense와 Budget 관계
    - expense는 budget의 상속관계로 설정한다. 이유는 expense(지출)은 budget(예산) 내에서 사용이 가능하기 때문이다.
        - 추가로 고려할 사항 expense와 budget의 상속관계가 서로 바뀌어야 할까? 왜냐하면 지출 기준으로 서비스가 동작하므로 ?
- Category와 Budget 은 N 대 N 관계로 설정한다.
    - 1(카테고리) 대 N(예산) 관계가 아닌가 ? 왜냐하면 예산별로 카테고리를 가지므로
        - 아니다 하나의 예산이 카테고리를 가지고 또 그 카테고리들은 여러개의 예산에 속할 수 있으므로 N 대 N 관계가 성립되어야 한다.

</details>

<br/>

## 코드리뷰 및 에러 해결

### 로그인 회원가입
- [회원가입](https://github.com/rivkode/budget_management/pull/4)

### 예산설정
- [예산설정](https://github.com/Wanted-Internship-Team-Careerly/Location-Based-Foodie-Service/pull/15)

<br/>

## Authors

<div align="center">

<br/>

![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white) </br>
<a href="https://github.com/rivkode">이종훈</a>

</div>
<br/>

## References

- [Awesome Readme Templates](https://awesomeopensource.com/project/elangosundar/awesome-README-templates)
- [Awesome README](https://github.com/matiassingers/awesome-readme)
- [How to write a Good readme](https://bulldogjob.com/news/449-how-to-write-a-good-readme-for-your-github-project)