package study.delete_propagation.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.delete_propagation.deletepropagation.EntityDeleteManager;
import study.delete_propagation.entity.club.Club;
import study.delete_propagation.entity.club.ClubRepository;
import study.delete_propagation.entity.member.Member;
import study.delete_propagation.entity.member.MemberRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final MemberRepository memberRepository;
    private final ClubRepository clubRepository;
    private final EntityDeleteManager entityDeleteManager;

    @GetMapping("/1")
    public String test1() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
//        Member member = memberRepository.findById(1L).get();
//        entityDeleteManager.deleteEntity(member);

        Club club = clubRepository.findById(1L).get();
        entityDeleteManager.deleteEntity(club);
        return "ok";
    }

}
