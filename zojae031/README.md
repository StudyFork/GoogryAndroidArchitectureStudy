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



# 5주차 2019-08-25

## 1. DataBinding

1. DatabindingUitl.bind() 
   - View를 인자로 받는다.
   - 특정 layout을 view형태로 inflate해서 바인드 해줘야 할때
   - bind는 view로 만드어진 것을 바인드 시켜줄 때

2. DatabindingUitl.inflate()
   - view를 만들면서 bind를 해주어야 할 때

3. DatabindingUitl.setContentView()
   - 액티비티 한정



## 2. 연산

1. &lt : <
2. &gt : >
3. &amp : &



## 3. Event Handling



```xml 
android:Onclick="@{ () -> view.onBackPressed()  }"
```



executePendingBindings() : UI 스레드가 바로 작동 할 수 있도록 ( 우선순위를 최상단으로 준다. )

- bind할때 **필수!!!**



BindingAdapter : java -> xml

InverseBindingAdapter : xml -> java

# 6주차 2019-09-22

#### - Kotlin의 기본은 Effective Java에 의거한 방식을 사용한것이라 기본을 지키는게 좋다.

Ex) class 

- java : inner class, 변수(Mutable)
- kotlin : static class, 변수(Immutable)

주체일때 run, apply ///객체를 다른곳에 사용할때 let, also

onPropertyChangeCallback

#### - 구글 아키텍쳐 디자인 가이드를 보고 해도 좋다

1. dialog vs dialog fragment



2. 왜 데이터는 번들로 옮길까



3. listener set 하면 안되는 이유



# 7주차 2019-09-29

### AAC ViewModel

ViewModel은 Activity에 관련되게 만들어진다.

ViewModel은 어디에 의존하여 동작하는지 결정된다.

OnClear 를 호출하면서 메모리참조를 해제한다 (=onDestroy())

ViewModelProviders를 써야하는 이유 -  fragment들끼리 같은 ViewModel을 공유 할 수 있다.

AAC ViewModel을 쓰면 Shared ViewModel을 사용할 수 있다.

FragmentActivity가 store를 하여 그놈을 참조하여 가져온다.



### LiveData 

옵저버를 감싼 클래스이다.

액티비티, 프래그먼트, 서비스 관련 생명주기가 살아있을때만 옵저버블 된다.



### MediatorLiveData

하나의 값이 바뀔때 같이 바뀌어야 하는 값


```kotlin
addSource(_source){

//TODO _source가 변화할때 바뀌는 로직

}
```



# 8주차 2019-10-13

안에서 여러번 호출되는것을 분리시킨다. (new...) -> 위로 올린다. 

(해결1) : 합성관계 (전체 객체가 없어지면 부분객체도 없어진다.) composition

의존성 주입 -> 밖에서 생성하여 인자로 넘겨준다

(해결2) :  집약관계 (전체객체가 없어져도 부분객체는 살아있다.)

@Provide : 객체를 생성해서 제공해주는곳

@Module : Provide들을 가지고 있는것

@Inject : 주입하는 것



single : SingleTon

factory : 달라고 요청할때마다 새로 만든다. (일반 생성)

Qualifiers (식별자)

single(named("mock")) <- 오버로딩 될때 리턴타입 정해주는것.

by : lazy

get : 바로 넣어주는것 



# 후기

아직도 onCreate에 모든 코드를 우겨넣고 수정할때마다 머리를 쥐어뜯습니까?

그렇다면 지금당장 신청합시다.

1. **코드리뷰를 통한 협업 간접체험**
   - 팀원들이 모두 유료로 등록하여 듣는 스터디이기 때문에 모두가 열정적인 최고의 학습환경입니다.
   - 선배기수분들의 코드리뷰로 알지 못하던 부분, 더 깊은 부분까지 생각해볼수 있는 기회를 가집니다.
2. **MVC -> MVP -> MVVM 세가지 주요 패턴의 장단점 직접 체험하기**
   - 만약 어느회사를 들어갔는데 특정 패턴만 쓴다 한다면 그 패턴을 공부하기도 벅찰것이며 패턴들의 장단점은 알지 못한 채 적용하기만 급급할것입니다. 모든 패턴을 사용해보고 왜 그 패턴을 사용하는지 숙지하고 사용합시다.
3. **러닝커브 낮추기**
   - 새로운 지식을 접목할 때, 익숙하지 않은 정보들은 배우기 어렵습니다. 지식을 습득하는곳에 돈을 아끼지 마세요. 그 돈으로 습득한 지식은 그 이상으로 돌아올 것이라 확신합니다.
   - 혼자 공부하는것도 고민하고 생각하는 과정에서 도움이 될 수 있습니다. 하지만 함께 공부하면 그 시너지는 배가 될 것입니다.



### 스스로 노력해야 얻는것이 더 많습니다. 

##### *무조건적인 주입식 교육을 원하시는 분들은 팀원에게 피해가 될 수 있습니다.*

