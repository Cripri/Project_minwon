package function.connector;

import function.pdfwriter.PDFWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static gui.mainframe.MainFrameState.civil;


public class testcon {
    public static void main(String[] args){
        Civil_Connector con = new Civil_Connector();
        con.start();

        // select all members
        List<Members> memList = con.selectAll(Members.class);
        memList.forEach(System.out::println);

        // update 예시
        if (!memList.isEmpty()) {
            Members m = memList.get(memList.size() - 1);
            m.setMember_name("강지후");
            con.update(m);
        }

//        try {
//            Thread.sleep(200); // update 스레드 처리 대기
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        // 다시 조회해서 출력
        memList = con.selectAll(Members.class);
        memList.forEach(System.out::println);

    }
}
