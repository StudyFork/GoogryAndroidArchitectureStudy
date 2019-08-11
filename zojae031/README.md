# Zojae031 (조재영)의 공간입니다.

# 1주차 2019_07_14

- Review Needed (내가 파트너에게 리뷰를 요청할 때)
- Answer Needed (리뷰를 달고 다시 응답을 요청 할 때)
- Merge Needed (석준님이 모든게 완료되었고 Merge를 시킬 때)
- Mentor Needed (석준님에게 리뷰를 요청 할 때)

<hr>

##### 과제, MVC 패턴을 적용하여 코인헬퍼 기본 UI 제작



# 2주차2019_07_21

- Model : 데이터를 어디서 가져오고 어디서 저장을 할지 처리를 하는 부분

#### Server (Remote) + DB,Pref,File,Cache (Local) = DataSource

- view는 데이터통신을 할 뿐 무슨 데이터가 오는지 알 필요가 없다.
- 중간에 관리해주는 역할 : Repository (로컬에서 오는가 리모트에서 오는가?)
- 레파지토리는 비즈니스로직을 가질 수 있다.
- 싱글톤으로 관리하기 때문에 변수를 가지거나 그러면 안된다.

Repostiory : 데이터를 가져오라는 액션

Remote, Local : 데이터를 가져오는 행위

**Repository <-> DataSource <-> Remote, Local**

<hr>

패키지 나누는 법

1. Data
2. Ui
3. Util - 공통으로 재사용 가능한 함수, 클래스
4. Extension 



액티비티나 서비스같은경우는 다른 앱에서 실행시킬 수 있게 설계가 되어있다.

액티비티는 단위로 나누어져야 한다. (종속성이 없어야 한다)

따라서 Bundle을 이용하여 데이터를 전송해야 한다.

# 3주차 2019-07-28

View <-> Presenter <-> model

### 1. View

- 화면에 보여주는 역할만 한다.
- 프레젠터에게 어떠한 데이터가 필요해! 라 요청만 한다
- 사람으로 비교하면 몸이다.

### 2. Presenter

- View에게 정보를 받아 Model로 요청을 한다.
- 응답받은 요청을 View에게 알려준다.
- 뇌다.

### 3. Model

- Presenter에게 요청을 받아 되돌려준다.
- 기억이다.





##### Rx를 사용하여 MVP 적용하는 과정

1. API
   - Single
2. RemoteDataSource 
   - subscribeOn
3. Repository                  
   - map
4. Presenter                   
   - observeOn
   - view.something()
5. View      
   - adapter.something()

# 4주차 2019-08-11

### 1. interface를 사용하여 받자

- 해당 클래스의 지표인 interface로 인자를 받아 변화에 유용하도록 한다.

### 2. context정보를 presenter가 모르도록 한다.

- 만약 model이나 presenter에서 필요로 한다면 만들어서 넘겨준다.

### 3. Repository, remote, local interface를 모두 만들자

