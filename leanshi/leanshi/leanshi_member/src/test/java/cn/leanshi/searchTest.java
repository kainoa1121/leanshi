package cn.leanshi;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * @author :ldq
 * @date:2018/8/2
 * @description:leanshi_member cn.leanshi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class searchTest {

	/*private MemberService memberService;

	*//*
	 *条件模糊查询
	 * *//*
	@Test
	@RequestMapping(value = "/stest")
	public void search(){
		Map<String,String> searchMap = new HashMap<String, String>();
		searchMap.put("mCode","00001");
		searchMap.put("mName","");
		searchMap.put("mobile","");
		searchMap.put("mNickName","");
		Map<String, Object> search = memberService.search(searchMap);
		System.out.println(search);
	}*/

}
