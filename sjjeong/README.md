# 여기는 스터디장 공간입니다.

## 스터디 초반 셋팅 과정
- upstream에 root/README.md파일에 본인 기수 항목에 이름/프로필링크/폴더링크를 추가한다.
- String.format("class%02d/%s", 본인기수, github_id)의 형태의 브랜치에서 Android Studio 프로젝트를 만든다음 git ignore를 추가한다.  
git ignore는 [git_ignore_generator](https://gitignore.io)에 들어가서 Android, Android Studio, Kotlin으로 만든다.  
이 작업을 끝내고 PR 만들어주세요.


## PR, commit
- 커밋 메시지(HW-n xxx)
- pr 리뷰에 코멘트 마다 나눠서 커밋 하고 코멘트에 커밋id 코멘트로 남기기
- 현재 브랜치를 upstream에서 pull 해서 merge하거나 rebase하기
- commit 하기전에 [Reformat code](https://www.jetbrains.com/help/ruby/reformat-and-rearrange-code.html)하기 
- commit 메시지를 의미있게 작성하기


## PR Label
- Review Needed: 리뷰어의 리뷰가 필요한 경우 추가
- Answer Needed: PR담당자의 응답이 필요한 경우 추가
- Mentor Needed: 멘토가 필요한 경우 추가
- Merge Needed: 모든 리뷰어가 Approve를 한 경우 추가


## kotlin coding convention 지키기
- [kotlinlang coding convention](https://kotlinlang.org/docs/reference/coding-conventions.html)
- [android developer coding convention](https://developer.android.com/kotlin/style-guide)

## 유용한 IntelliJ Plugin
- [WIFI ADB ULTIMATE](https://plugins.jetbrains.com/plugin/9207-wifi-adb-ultimate)
- [JSON To Kotlin Class ​(JsonToKotlinClass)​](https://plugins.jetbrains.com/plugin/9960-json-to-kotlin-class-jsontokotlinclass-)
- [Presentation Assistant](https://plugins.jetbrains.com/plugin/7345-presentation-assistant)