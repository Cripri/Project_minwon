package function.complaintkeyword;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ComplaintClassifier {
	
	// 나중에 DB에서 가져오게끔 해도 됨
	static String[] corpList = new String[] {"한국도로공사", "한국철도공사", "한국전력공사", "한국지역난방공사", "도로교통공단", "한국환경공단"};
    
    static HashMap<String, ArrayList<String>> keywordList = new HashMap<>();
    
    static {
    	for (String corp : corpList) {
            keywordList.put(corp, new ArrayList<>());
        }
    	
    	for (String corp : corpList) {
    		String path = String.format("resources/complaint_keyword/%s.txt", corp);
			
    		try (FileReader fin = new FileReader(path);
    				BufferedReader bin = new BufferedReader(fin)) {
    			 
    			String line;
    			ArrayList<String> list = keywordList.get(corp);
    			while ((line = bin.readLine()) != null) {
    				line = line.trim();
    				if (line.startsWith("\uFEFF")) {
    				    line = line.substring(1);
    				}
                    if (!line.isEmpty()) {
                        list.add(line);
                    }
    			}
    		} catch (IOException e1) {
    			System.out.println("파일 읽기 실패: " + path);
    			e1.printStackTrace();
    		}
		}
    }

    public static String classify(String text) {
    	String fallback = null;

    	text = text.trim(); 
    	for (Entry<String, ArrayList<String>> entry : keywordList.entrySet()) {
            String company = entry.getKey();
            ArrayList<String> keywords = entry.getValue();

            int score = 0;
            for (String keyword : keywords) {
            	
                if (text.contains(keyword)) {
                    score++;
                }
            }

            // 키워드가 3개 이상 있다면 회사 리턴
            if (score >= 3) {
                return company;
            }

            // 키워드가 1개 이상 3개 미만이면 후보로 저장
            if (score >= 1 && fallback == null) {
                fallback = company;
            }
        }

        // 키워드가 3개 이상 없고, 3개 미만이면 후보 리턴, 0개면 판별 불가
        return fallback != null ? fallback : "판별 불가";
    }
    
    // 키워드 추가하기
    public static void appendKeyword(String company, String newKeyword) {
    	if (!keywordList.containsKey(company)) {
            System.out.println("해당 회사가 목록에 없습니다: " + company);
            return;
        }
    	
        String path = String.format("resources/complaint_keyword/%s.txt", company);

        try (FileWriter fw = new FileWriter(path, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(newKeyword);
            bw.newLine(); // 줄바꿈 추가

        } catch (IOException e) {
            System.out.println("키워드 추가 실패: " + path);
            e.printStackTrace();
        }
    }
}
