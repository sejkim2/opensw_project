package com.example.app.movie;

import com.example.app.genre.Genre;
import com.example.app.genre.GenreRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class MovieDataInitializer {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @PostConstruct
    public void initDummyMovies() {
        if (movieRepository.count() > 0) return;

        List<Genre> allGenres = genreRepository.findAll();

        Genre action = findByName(allGenres, "Action");
        Genre drama = findByName(allGenres, "Drama");
        Genre romance = findByName(allGenres, "Romance");
        Genre sciFi = findByName(allGenres, "Science Fiction");
        Genre horror = findByName(allGenres, "Horror");
        Genre fantasy = findByName(allGenres, "Fantasy");
        Genre comedy = findByName(allGenres, "Comedy");
        Genre animation = findByName(allGenres, "Animation");
        Genre thriller = findByName(allGenres, "Thriller");
        Genre other = findByName(allGenres, "Other");

        List<Movie> movies = Arrays.asList( 
                Movie.builder()
                        .title("극한직업")
                        .description("낮에는 치킨장사! 밤에는 잠복근무!\n" + //
                                                                "지금까지 이런 수사는 없었다!\n" + //
                                                                "\n" + //
                                                                "불철주야 달리고 구르지만 실적은 바닥, 급기야 해체 위기를 맞는 마약반!\n" + //
                                                                "더 이상 물러설 곳이 없는 팀의 맏형 고 반장(류승룡)은 국제 범죄조직의 국내 마약 밀반입 정황을 포착하고\n" + //
                                                                "장 형사(이하늬), 마 형사(진선규), 영호(이동휘), 재훈(공명)까지 4명의 팀원들과 함께 잠복 수사에 나선다.\n" + //
                                                                "마약반은 24시간 감시를 위해 범죄조직의 아지트 앞 치킨집을 인수해 위장 창업을 하게 되고,\n" + //
                                                                "뜻밖의 절대미각을 지닌 마 형사의 숨은 재능으로 치킨집은 일약 맛집으로 입소문이 나기 시작한다.\n" + //
                                                                "수사는 뒷전, 치킨장사로 눈코 뜰 새 없이 바빠진 마약반에 어느 날 절호의 기회가 찾아오는데…\n" + //
                                                                "\n" + //
                                                                "범인을 잡을 것인가, 닭을 잡을 것인가!")
                        .imageUrl("/images/movie1.jpeg") 
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(comedy)))
                        .build(),

                Movie.builder()
                        .title("내안의 그놈")
                        .description("제대로 바뀐 아재와 고딩,\n" + //
                                                                "웃음 대환장 파티!\n" + //
                                                                "\n" + //
                                                                "옥상에서 떨어진 고등학생 동현(진영)이 길을 가던 엘리트 아재 판수(박성웅)를 덮치면서 제대로 바뀐다!? 게다가 판수는 동현의 몸으로 첫사랑 미선(라미란)과 존재도 몰랐던 딸 현정(이수민)을 만나게 되는데...\n" + //
                                                                "\n" + //
                                                                "대유잼의 향연,\n" + //
                                                                "넌 이미 웃고 있다!")
                        .imageUrl("/images/movie2.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama)))
                        .build(),

                Movie.builder()
                        .title("태극기 휘날리며")
                        .description("“우린 반드시 살아서 돌아가야해”\n" + //
                                                                "\n" + //
                                                                "가족의 생계를 책임지기 위해 열심히 살아가는 ‘진태’(장동건)는 약혼녀 ‘영신’(이은주)과의 결혼과, 세상에서 가장 소중하게 생각하는 동생 ‘진석’(원빈)의 대학진학을 위해 어려운 상황에서도 씩씩하게 생활을 해나간다.\n" + //
                                                                "\n" + //
                                                                "1950년 6월의 어느 날, 한반도에 전쟁이 일어났다는 호회가 배포되고, 두 형제는 평온한 일상에서 갑작스레 전쟁터로 내몰린다. 훈련받을 시간조차 없이 국군 최후의 보루인 낙동강 방어선으로 실전 투입된 ‘진태’와 ‘진석’. 동생과 같은 소대에 배치된 ‘진태’는 아직 학생인 동생의 징집해제를 위해 대대장을 만나게 되고, 동생의 제대를 위해 자신이 해야 할 최선의 것이 무엇인지를 느끼게 된다.\n" + //
                                                                "\n" + //
                                                                "그리고 동생의 생존을 위해 총을 들며 영웅이 되기를 자처하게 되고,\n" + //
                                                                "끝내 생각지도 못한 운명의 덫이 두 형제를 기다리고 있는데…")
                        .imageUrl("/images/movie3.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(other)))
                        .build(),

                Movie.builder()
                        .title("수상한 그녀")
                        .description("스무살 꽃처녀가 된 칠순 할매의 빛나는 전성기가 시작된다!\n" + //
                                                                "\n" + //
                                                                "국립대 교수인 아들 자랑이 유일한 낙인 욕쟁이 칠순 할매 오말순은 어느 날, 가족들이 자신을 요양원으로 독립(?)시키려 한다는 청천벽력 같은 사실을 알게 된다. 뒤숭숭한 마음을 안고 밤길을 방황하던 할매 말순은 오묘한 불빛에 이끌려 '청춘 사진관'으로 들어간다. 난생 처음 곱게 꽃단장을 하고 영정사진을 찍고 나오는 길, 그녀는 버스 차창 밖에 비친 자신의 얼굴을 보고 경악을 금치 못한다. 오드리 헵번처럼 뽀얀 피부, 날렵한 몸매... 주름진 할매에서 탱탱한 꽃처녀의 몸으로 돌아간 것! 아무도 알아보지 못하는 자신의 젊은 모습에 그녀는 스무 살 '오두리'가 되어 빛나는 전성기를 즐겨 보기로 마음 먹는데...\n" + //
                                                                "\n" + //
                                                                "2014년 새해, 대한민국에 웃음 보따리를 안겨줄 <수상한 그녀>가 온다!")
                        .imageUrl("/images/movie4.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama, comedy)))
                        .build(),

                Movie.builder()
                        .title("범죄도시1")
                        .description("오늘 밤, 다 쓸어버린다!\n" + //
                                                                "\n" + //
                                                                "2004년 서울…\n" + //
                                                                "하얼빈에서 넘어와 단숨에 기존 조직들을 장악하고\n" + //
                                                                "가장 강력한 세력인 춘식이파 보스 ‘황사장(조재윤 분)’까지 위협하며\n" + //
                                                                "도시 일대의 최강자로 급부상한 신흥범죄조직의 악랄한 보스 ‘장첸(윤계상 분)’.\n" + //
                                                                "\n" + //
                                                                "대한민국을 뒤흔든 ‘장첸(윤계상 분)’ 일당을 잡기 위해\n" + //
                                                                "오직 주먹 한방으로 도시의 평화를 유지해 온 괴물형사 ‘마석도(마동석 분)’와\n" + //
                                                                "인간미 넘치는 든든한 리더 ‘전일만(최귀화 분)’ 반장이 이끄는 강력반은\n" + //
                                                                "나쁜 놈들을 한방에 쓸어버릴 끝.짱.나.는. 작전을 세우는데…\n" + //
                                                                "\n" + //
                                                                "통쾌하고! 화끈하고! 살벌하게!\n" + //
                                                                "올 추석, 나쁜 놈들 때려잡는 강력반 형사들의 ‘조폭소탕작전’이 시작된다!")
                        .imageUrl("/images/movie5.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(thriller, comedy)))
                        .build(),
                
                        Movie.builder()
                        .title("쇼생크 탈출")
                        .description("두려움은 너를 죄수로 가두고 희망은 너를 자유롭게 하리라\n" + //
                                                                "\n" + //
                                                                "촉망 받던 은행 부지점장 ‘앤디(팀 로빈스 扮)’는 아내와 그 애인을 살해한 혐의로 종신형을 받고 쇼생크 교도소에 수감된다. 강력범들이 수감된 이곳에서 재소자들은 짐승 취급 당하고, 혹여 간수 눈에 잘못 보였다가는 개죽음 당하기 십상이다. 처음엔 적응 못하던 ‘앤디’는 교도소 내 모든 물건을 구해주는 ‘레드(모건 프리먼 扮)’와 친해지며 교도소 생활에 적응하려 하지만, 악질 재소자에게 걸려 강간까지 당한다.\n" + //
                                                                "그러던 어느 날, 간수장의 세금 면제를 도와주며 간수들의 비공식 회계사로 일하게 되고, 마침내는 소장의 검은 돈까지 관리해주게 된다. 덕분에 교도소 내 도서관을 열 수 있게 되었을 무렵, 신참내기 ‘토미(길 벨로우스 分)’로부터 ‘앤디’의 무죄를 입증할 기회를 얻지만, 노튼 소장은 ‘앤디’를 독방에 가두고 ‘토미’를 무참히 죽여버리는데...")
                        .imageUrl("/images/movie6.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(other)))
                        .build(),

                        Movie.builder()
                        .title("센과 치히로의 행방불명")
                        .description("금지된 세계의 문이 열렸다!\n" + //
                                                                "이사 가던 날, 수상한 터널을 지나자 인간에게는 금지된 신들의 세계로 오게 된 치히로...\n" + //
                                                                "신들의 음식을 먹은 치히로의 부모님은 돼지로 변해버린다.\n" + //
                                                                "\n" + //
                                                                "“걱정마, 내가 꼭 구해줄게…”\n" + //
                                                                "겁에 질린 치히로에게 다가온 정체불명의 소년 하쿠.\n" + //
                                                                "그의 따뜻한 말에 힘을 얻은 치히로는\n" + //
                                                                "인간 세계로 돌아가기 위해 사상 초유의 미션을 시작하는데…")
                        .imageUrl("/images/movie7.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(animation)))
                        .build(),

                        Movie.builder()
                        .title("인터스텔라")
                        .description("“우린 답을 찾을 거야, 늘 그랬듯이”\n" + //
                                                                "세계 각국의 정부와 경제가 완전히 붕괴된 미래가 다가온다. 지난 20세기에 범한 잘못이 전 세계적인 식량 부족을 불러왔고, NASA도 해체되었다. 이때 시공간에 불가사의한 틈이 열리고, 남은 자들에게는 이 곳을 탐험해 인류를 구해야 하는 임무가 주어진다. 사랑하는 가족들을 뒤로 한 채 인류라는 더 큰 가족을 위해, 그들은 이제 희망을 찾아 우주로 간다. 그리고 우린 답을 찾을 것이다. 늘 그랬듯이...")
                        .imageUrl("/images/movie8.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(sciFi)))
                        .build(),

                        Movie.builder()
                        .title("스파이더맨2")
                        .description("파워, 운명 그리고 사랑… 이젠 선택만이 남았다!\n" + //
                                                                "\n" + //
                                                                "유전자 조작 거미에 물려 스파이더맨이 된 피터 파커(토비 맥과이어). 모두에겐 영웅이지만 정작 자신에겐 어려운 일 투성이다. 스파이더맨의 신분을 밝힐 수 없는 상황에서 사랑하는 메리 제인(커스틴 던스트)과의 약속은 어긋나기 일쑤고, 스파이더맨에게 죽은 아버지의 복수심에 불타는 친구 해리의 모습 역시 모른 척할 수 밖에 없다.\n" + //
                                                                "\n" + //
                                                                "한편 피터가 존경하는 핵물리학자 옥타비우스 교수는 실험중 사고로 기계촉수를 휘두르는 악의 화신 ‘닥터 옥토퍼스’가 된다. 이제 과학재단을 운영하는 해리가 닥터 오크에게 절실한 트리늄을 빌미로 거절못할 제안을 하면서 도시 전체가 걷잡을 수 없는 위험에 휘말리게 되는데…")
                        .imageUrl("/images/movie9.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(action)))
                        .build(),

                        Movie.builder()
                        .title("나니야 연대기")
                        .description("페벤시 가의 네 남매는 우연히 하얀 마녀가 지배하고 있는 신비의 나라 나니아로 들어서게 된다. 그들은 고귀한 사자 아슬란과 함께 마녀를 상대하게 되는데...\n" + //
                                                                "")
                        .imageUrl("/images/movie10.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(fantasy)))
                        .build(),

                        Movie.builder()
                        .title("하울의 움직이는 성")
                        .description("19세기 말의 마법과 과학이 공존하는 어느 세계. 해터 모자 공방의 주인의 장녀인 18살 소녀 소피는 길을 가던 중 우연히 마법사 하울을 만난다.\n" + //
                                                                "\n" + //
                                                                "그러나 그날 밤, 황야의 마녀라 불리는 의문의 여성에게 노파가 되는 저주를 받고 황야로 여행을 떠난다. 그리고 그때 만난 하울의 성에 도착해 그 곳에서 여러 일을 도우며 지내게 되는데...")
                        .imageUrl("/images/movie11.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(animation, fantasy, romance)))
                        .build(),

                        Movie.builder()
                        .title("인사이드 아웃 2")
                        .description("디즈니·픽사의 대표작 <인사이드 아웃> 새로운 감정과 함께 돌아오다! 13살이 된 라일리의 행복을 위해 매일 바쁘게 머릿속 감정 컨트롤 본부를 운영하는 ‘기쁨’, ‘슬픔’, ‘버럭’, ‘까칠’, ‘소심’. 그러던 어느 날, 낯선 감정인 ‘불안’, ‘당황’, ‘따분’, ‘부럽’이가 본부에 등장하고, 언제나 최악의 상황을 대비하며 제멋대로인 ‘불안’이와 기존 감정들은 계속 충돌한다. 결국 새로운 감정들에 의해 본부에서 쫓겨나게 된 기존 감정들은 다시 본부로 돌아가기 위해 위험천만한 모험을 시작하는데… 2024년, 전 세계를 공감으로 물들인 유쾌한 상상이 다시 시작된다!")
                        .imageUrl("/images/movie12.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(animation)))
                        .build(),

                        Movie.builder()
                        .title("키싱 부스 2")
                        .description("하버드간 완벽한 롱디 남친에게 미모의 여사친이, 졸업반 엘에게는 섹시 전학생이 나타난다. 키싱부스가 맺어준 이 커플의 장거리 연애에는 달콤한 장애물이 너무 많은데… 아찔하게 돌아온 키싱부스2, 영업 개시!")
                        .imageUrl("/images/movie13.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(romance, comedy)))
                        .build(),

                        Movie.builder()
                        .title("나우 유 씨 미 : 마술 사기단")
                        .description("1년 전, 길거리에서 소소한 공연이나 하는 정도였던 무명의 마술사 4명. 그들은 누군가가 보낸 초대장에 의해 한 자리에 모이게 되고 이후 '포 호스맨 (Four Horsemen)' 이란 마술팀을 결성한다. 그리고 라스베가스 마술쇼에서 파리은행의 비자금을 통째로 털어버리는 쇼를 멋지게 성공시키면서 순식간에 세계적인 톱스타가 된다. 하지만 제아무리 마술이라지만 은행을 터는 행위는 엄연한 범죄. 네 사람은 곧 경찰에 체포된다. FBI 요원 딜런 로즈는 마술로 은행을 털어버렸다는 황당한 강도사건소식을 듣고는 황당함을 금치 못했다. 그래서 문제의 범죄자들을 잡아들여 취조를 했지만 그들은 모든건 마술이라는 말만 할 뿐, 결코 범행동기에 대해서 말하는 법은 없었다. 게다가 이들이 직접적으로 은행을 털었다는 확실한 증거도 없는 상황. 딜런은 결국 그들을 증거불충분으로 풀어주는수밖에 없었고 그때부터 반드시 그들을 잡아들이겠다고 결심하며 포 호스맨과의 전쟁을 선포했고 마술트릭을 파헤치는 마술 비판가 태디어스 브래들리를 섭외한다. 하지만 포 호스맨은 경찰들을 비웃기라도 하듯 점점 대담한 마술행각을 펼쳤고 딜런을 비롯한 FBI 역시 이런 그들을 잡아들이기 위해 발바닥에 불이 나도록 뛰어나기 시작한다. 과연, 누구의 승리로 끝날까?")
                        .imageUrl("/images/movie14.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(action, thriller, fantasy, comedy)))
                        .build(),

                        Movie.builder()
                        .title("어바웃 타임")
                        .description("모태솔로 팀(돔놀 글리슨)은 성인이 된 날, 아버지(빌 나이)로부터 놀랄만한 가문의 비밀을 듣게 된다. 바로 시간을 되돌릴 수 있는 능력이 있다는 것! 그것이 비록 히틀러를 죽이거나 여신과 뜨거운 사랑을 할 수는 없지만, 여자친구는 만들어 줄 순 있으리.. 꿈을 위해 런던으로 간 팀은 우연히 만난 사랑스러운 여인 메리에게 첫눈에 반하게 된다. 그녀의 사랑을 얻기 위해 자신의 특별한 능력을 마음껏 발휘하는 팀. 어설픈 대시, 어색한 웃음은 리와인드! 뜨거웠던 밤은 더욱 뜨겁게 리플레이! 꿈에 그리던 그녀와 매일매일 최고의 순간을 보낸다. 하지만 그와 그녀의 사랑이 완벽해질수록 팀을 둘러싼 주변 상황들은 미묘하게 엇갈리고, 예상치 못한 사건들이 여기저기 나타나기 시작하는데… 어떠한 순간을 다시 살게 된다면, 과연 완벽한 사랑을 이룰 수 있을까?")
                        .imageUrl("/images/movie15.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(romance, comedy)))
                        .build(),

                        Movie.builder()
                        .title("노트북")
                        .description("[영혼을 바쳐 한 사람을 사랑했고, 그것만으로도 성공한 인생입니다] 노아(라이언 고슬링)는 여름 휴가를 맞아 잠시 시골로 내려온 밝고 아름다운 부잣집 아가씨 앨리(레이첼 맥아담스)에게 한 눈에 반한다. 서로에게 빠르게 빠져들어 뜨거운 여름을 보내는 두 사람. 그러나 너무나 다른 가정 환경으로 인해 강렬했던 노아와 앨리의 첫사랑은 이별을 맞는다. 시간이 흐른 후, 여전히 서로를 가슴속에 묻은 채 살던 두 사람은 우연히 재회하게 된다. 그러나 앨리의 곁엔 결혼을 약속한 완벽한 약혼자가 있는데...")
                        .imageUrl("/images/movie16.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama, romance)))
                        .build(),

                        Movie.builder()
                        .title("레미제라블")
                        .description("빵 한 조각을 훔친 죄로 19년의 감옥살이를 한 장발장(휴 잭맨). 전과자라는 이유만으로 모두의 박해를 받던 장발장은 우연히 만난 신부의 손길 아래 구원을 받고 새로운 삶을 결심한다. 정체를 숨기고 마들렌이라는 새 이름으로 가난한 이들을 도우며 지내던 장발장은 운명의 여인, 판틴(앤 해서웨이)과 마주치고, 죽음을 눈앞에 둔 판틴은 자신의 유일한 희망인 딸, 코제트(아만다 사이프리드)를 장발장에게 부탁한다. 그러나 코제트를 만나기도 전에 경감 자베르(러셀 크로우)는 장발장의 진짜 정체를 알아차리고, 오래된 누명으로 다시 체포된 장발장은 코제트를 찾아 탈옥을 감행하는데…")
                        .imageUrl("/images/movie17.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama)))
                        .build(),

                        Movie.builder()
                        .title("무파사 : 라이온 킹")
                        .description("외로운 고아에서 전설적인 왕으로 거듭난 ‘무파사’의 숨겨진 이야기가 베일을 벗는다! 길을 잃고 혼자가 된 새끼 사자 ‘무파사’는 광활한 야생을 떠돌던 중 왕의 혈통이자 예정된 후계자 ‘타카(스카)’와 우연히 만나게 된다. 마치 친형제처럼 끈끈한 우애를 나누며 함께 자란 ‘무파사’와 ‘타카’는 운명을 개척하기 위해 거대한 여정을 함께 떠난다. 한 치 앞을 알 수 없는 적들의 위협 속에서 두 형제의 끈끈했던 유대에 금이 가기 시작하고 예상치 못한 위기까지 맞닥뜨리게 되는데…")
                        .imageUrl("/images/movie18.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(animation)))
                        .build(),

                        Movie.builder()
                        .title("야당")
                        .description("대한민국 마약 수사의 뒷거래 모든 것은 야당으로부터 시작된다! 누명을 쓰고 교도소에 수감된 이강수(강하늘)는 검사 구관희(유해진)로부터 감형을 조건으로 야당을 제안받는다. 강수는 관희의 야당이 돼 마약 수사를 뒤흔들기 시작하고, 출세에 대한 야심이 가득한 관희는 굵직한 실적을 올려 탄탄대로의 승진을 거듭한다. 한편, 마약수사대 형사 오상재(박해준)는 수사 과정에서 강수의 야당질로 번번이 허탕을 치고, 끈질긴 집념으로 강수와 관희의 관계를 파고든다. 마약판을 설계하는 브로커 강수, 더 높은 곳에 오르려는 관희, 마약 범죄 소탕에 모든 것을 건 상재. 세 사람은 각자 다른 이해관계로 얽히기 시작하는데…")
                        .imageUrl("/images/movie19.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(action)))
                        .build(),

                        Movie.builder()
                        .title("해리포터와 마법사의 돌")
                        .description("친척집에서 구박받는 생활을 하던 해리는 11살 생일을 앞두고 호그와트 마법학교로부터 입학초대장을 받고 자신이 마법사라는 사실을 알게 된다. 해리는 호그와트 마법학교로 가는 열차에서 친구 론, 헤르미온느를 사귀고 마법, 신비, 모험으로 가득한 학교생활을 시작한다.")
                        .imageUrl("/images/movie20.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(fantasy)))
                        .build(),

                        Movie.builder()
                        .title("위대한 쇼맨")
                        .description("쇼 비즈니스의 창시자이자, 꿈의 무대로 전세계를 매료시킨 남자 ‘바넘’의 이야기에서 영감을 받아 탄생한 오리지널 뮤지컬 영화 <위대한 쇼맨>. <레미제라블> 이후 다시 뮤지컬 영화로 돌아온 휴 잭맨부터 잭 에프론, 미셸 윌리엄스, 레베카 퍼거슨, 젠다야까지 할리우드 최고의 배우들이 합류해 환상적인 앙상블을 선보인다. 여기에 <미녀와 야수> 제작진과 <라라랜드> 작사팀의 합류로 더욱 풍성해진 비주얼과 스토리, 음악까지 선보일 <위대한 쇼맨>은 ‘우리는 누구나 특별하다’는 메시지로 관객들에게 재미는 물론, 감동까지 선사할 것이다. THIS IS ME! 우리는 누구나 특별하다!")
                        .imageUrl("/images/movie21.jpeg")
                        .averageRating(0.0)
                        .genres(new HashSet<>(List.of(drama)))
                        .build()
        );

        movieRepository.saveAll(movies);
    }

    private Genre findByName(List<Genre> genres, String name) {
        return genres.stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Genre not found: " + name));
    }
}
