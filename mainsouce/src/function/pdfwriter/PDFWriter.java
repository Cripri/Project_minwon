package function.pdfwriter;

import function.connector.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static gui.mainframe.MainFrameState.civil;

public class PDFWriter {
    public PDFWriter(int simple_doc_pk,String rrn,Civil_Connector con, String outputPath) {
        QueryRequest<Simple_doc> target = new QueryRequest<>(
                "select * from simple_doc where simple_doc_code like ?",
                simple_doc_pk,
                Simple_doc.class,
                con
        );
        Simple_doc data = target.getSingleResult();

        QueryRequest<Members> userdata = new QueryRequest<>(
                "select * from Members where MEMBER_CODE like ?",
                data.getMember_code(),
                Members.class,
                con
        );
        Members mem = userdata.getSingleResult();


        QueryRequest<District> dist = new QueryRequest<>(
                "select * from District where DISTRICT_CODE like ?",
                data.getDistrict_code(),
                District.class,
                con
        );
        District dis = dist.getSingleResult();


        if(data.getComplaint_category_code().contains("AA")){
            resident_registration(data, rrn, mem, dis, outputPath);
        }else if(data.getComplaint_category_code().equals("AB")){
            resident_registration_card(data, rrn, mem, dis, outputPath);
        }
    }
    public PDFWriter(int simple_doc_pk,String rrn,Civil_Connector con) {
        QueryRequest<Simple_doc> target = new QueryRequest<>(
                "select * from simple_doc where simple_doc_code like ?",
                simple_doc_pk,
                Simple_doc.class,
                con
        );
        Simple_doc data = target.getSingleResult();

        QueryRequest<Members> userdata = new QueryRequest<>(
                "select * from Members where MEMBER_CODE like ?",
                data.getMember_code(),
                Members.class,
                con
        );
        Members mem = userdata.getSingleResult();


        QueryRequest<District> dist = new QueryRequest<>(
                "select * from District where DISTRICT_CODE like ?",
                data.getDistrict_code(),
                District.class,
                con
        );
        District dis = dist.getSingleResult();

        String outputPath = "resources/pdf/temp.pdf";
        if(data.getComplaint_category_code().contains("AA")){
            resident_registration(data, rrn, mem, dis, outputPath);
        }else if(data.getComplaint_category_code().equals("AB")){
            resident_registration_card(data, rrn, mem, dis, outputPath);
        }
    }

    private void resident_registration(Simple_doc data,String rrn,Members user,District dis, String outputPath){
        String filePath = "resources/pdf/주민등록표 열람 또는 등ㆍ초본 교부 신청서.pdf";  // 기존 PDF 경로
        String ROOT_FONT_PATH = "resources/font/NotoSansKR-VariableFont_wght.ttf";
        String check = "○";
        String check2 = "Ｖ";

        Date today = data.getComplete_date();
        if (today == null) {
            today = new Date();  // null일 때 현재 날짜로 대체
        }

        SimpleDateFormat toYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat toMonth = new SimpleDateFormat("MM");
        SimpleDateFormat toDay = new SimpleDateFormat("dd");
        String nowYear = toYear.format(today);
        String nowMonth = toMonth.format(today);
        String nowDay = toDay.format(today);

        try (PDDocument document = PDDocument.load(new File(filePath));) {
            PDPage page = document.getPage(0); // 첫 페이지 가져오기

            InputStream fontStream = new FileInputStream(new File(ROOT_FONT_PATH));
            PDFont font = PDType0Font.load(document, fontStream);

            PDImageXObject pdImage = PDImageXObject.createFromFile("resources/sign/sign.png", document);

            try (PDPageContentStream contentStream = new PDPageContentStream(
                    document,
                    page,
                    PDPageContentStream.AppendMode.APPEND, // 기존 내용 유지 + 추가
                    true,
                    true
            )) {
                // 1페이지 이미지 + 내용 삽입
                contentStream.drawImage(pdImage,250,630,50 ,50); //위쪽 싸인
                writeText(document,page,user.getMember_name(),font,170,670); // 이름
                writeText(document,page,rrn,font,400,670); // 주민번호
                writeText(document,page,dis.getSd_name(),font,170,620); // 시도
                writeText(document,page,dis.getSgg_name(),font,300,620); // 시 군 구
                writeText(document,page,"본인",font,190,530); // 본 인
                writeText(document,page,user.getMember_phonenum(),font,400,530); //전화번호
                //writeText(document,page,check,font,113 ,493); //수수료면제
            }

            page = document.getPage(1); // 페이지 변경
            try (PDPageContentStream contentStream2 = new PDPageContentStream(
                    document,
                    page,
                    PDPageContentStream.AppendMode.APPEND,
                    true,
                    true
            )) {
                System.out.println(data.getComplaint_category_code());
                if(data.getComplaint_category_code().equals("AA001")) {
                    writeText(document, page, check, font, 166, 750); //등본
                    writeText(document,page,data.getDoc_count().toString(),font,122 ,563); // 등본 부수
                    if(data.getAll_Included().equals("Y")){
                        writeText(document,page,check,font,172 ,692); //등본 전부
                    }else{
                        if(data.getaddress_history().equals("Y")) {
                            if(data.getAddress_history_years() != null){
                                writeText(document, page, check, font, 390, 665); //주소 변동사항 최근_년포함
                                writeText(document,page,data.getAddress_history_years().toString(),font,493 ,665); //_년 포함
                            }else if(data.getAddress_history_years() == null){
                                writeText(document, page, check, font, 305, 665); //주소 변동사항 전체 포함
                            }
                        }

                        writeText(document, page, checkedOrEmpty(data.gethousehold_reason(), check), font, 504, 644); //세대 구성 사유

                        writeText(document,page,checkedOrEmpty(data.gethousehold_date(),check),font,504 ,623); //세대 구성 일자

                        writeText(document,page,checkedOrEmpty(data.getoccurrence_date(),check),font,504 ,601); //발생일 / 신고일

                        if(data.getprevious_address().equals("Y")) {
                            writeText(document, page, check, font, 397, 580); //변동 사유
                            if(data.getprevious_address_self().equals("Y")) {
                                writeText(document, page, check2, font, 445, 580); //세대
                            }
                            if(data.getprevious_address_member().equals("Y")) {
                                writeText(document, page, check2, font, 493, 580); //세대원
                            }
                        }

                        if(data.gethead_name().equals("Y")) {
                            writeText(document, page, check, font, 504, 557); //교부 대상자 외 세대주 세대원 외국인등
                        }

                        if(data.getrrn_last7().equals("Y")) {
                            writeText(document, page, check, font, 395, 536); //주민등록번호 뒷자리 포함
                            writeText(document,page,checkedOrEmpty(data.getrrn_last7_self(),check2),font,443 ,536); //세대
                            writeText(document,page,checkedOrEmpty(data.getrrn_last7_member(),check2),font,490 ,536); //세대원
                        }

                        writeText(document,page,checkedOrEmpty(data.gethead_relationship(),check),font,504 ,514); // 세대원의 세대주와의 관계

                        writeText(document,page,checkedOrEmpty(data.getRoommate(),check),font,504 ,493); // 동거인
                    }
                } else{
                    writeText(document, page, check, font, 347, 750); //초본
                    writeText(document,page,data.getDoc_count().toString(),font,122 ,372 ); // 초본 부수
                    if(data.getAll_Included().equals("Y")){
                        writeText(document,page,check,font,394 ,692); //초본 전부
                    }else{
                        writeText(document,page,checkedOrEmpty(data.getpersonal_change_details(),check),font,504 ,471); // 개인 인적사항 변경 내용

                        if(data.getaddress_history().equals("Y")) {
                            if(data.getAddress_history_years() > 0){
                                writeText(document,page,check,font,395 ,445); //직접입력
                                writeText(document,page,data.getAddress_history_years().toString(),font,496 ,445); //_년 포함
                            }else{
                                writeText(document, page, check, font, 315, 445); //과거의 주소 변동 사항 전체
                            }
                        }

                        writeText(document,page,checkedOrEmpty(data.gethead_relationship(),check),font,504 ,424); // 과거의 주소 변동 사항 중 세대주의 성명과 세대주와의 관계

                        writeText(document,page,checkedOrEmpty(data.getrrn_last7(),check),font,504 ,405); // 주민등록번호 뒷자리

                        writeText(document,page,checkedOrEmpty(data.gethead_relationship(),check),font,504 ,386); // 세대주의 성명과 세대주와의 관계

                        writeText(document,page,checkedOrEmpty(data.getoccurrence_date(),check),font,504 ,368); // 발생일 / 신고일


                        writeText(document,page,checkedOrEmpty(data.getprevious_address(),check),font,504 ,350); // 변동 사유
                        if(data.getmilitary_service().equals("Y")){
                            writeText(document,page,check,font,325 ,332); //병역사항
                            if(data.getMilitary_service_full().equals("Y")){
                                writeText(document,page,check2,font,501 ,332); //전체
                            }else{
                                writeText(document,page,check2,font,375 ,332); //기본
                            }
                        }
                        writeText(document,page,checkedOrEmpty(data.getid_number(),check),font,500 ,310); // 국내거소신고번호 / 외국인등록번호
                    }
                }

                //writeText(document,page,"몰라용",font,122 ,288 ); // 용도 및 목적
                //writeText(document,page,"나",font,122 ,245 ); // 증명 자료


                writeText(document,page,nowYear,font,400 ,187); // 연
                writeText(document,page,nowMonth,font,468 ,187); // 월
                writeText(document,page,nowDay,font,520 ,187); // 일

                writeText(document,page,user.getMember_name(),font,400,40); //이름

                contentStream2.drawImage(pdImage,500,20,50,50); // 싸인
            }

            // 새 파일로 저장
            document.save(outputPath);
            System.out.println("텍스트 추가 완료!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void resident_registration_card(Simple_doc data, String rrn, Members user, District dis, String outputPath){



    }

    private void writeText(PDDocument doc, PDPage page, String str, PDFont font, int tx, int ty) throws IOException {
        try(PDPageContentStream con = new PDPageContentStream(doc,page,PDPageContentStream.AppendMode.APPEND,true,true)){
            con.beginText();
            con.setFont(font,14);
            con.newLineAtOffset(tx,ty);
            con.showText(str);
            con.endText();
        }
    }

    private String checkedOrEmpty(String value, String checkMark) {
        return "Y".equalsIgnoreCase(value) ? checkMark : "";
    }

}
