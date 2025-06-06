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
