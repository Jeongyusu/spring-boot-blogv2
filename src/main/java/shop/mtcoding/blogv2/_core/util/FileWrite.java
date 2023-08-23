package shop.mtcoding.blogv2._core.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import shop.mtcoding.blogv2._core.error.ex.MyException;

import shop.mtcoding.blogv2.user.UserRequest;

public class FileWrite {

    public static String fileWrite(UserRequest.UpdateDTO updateDTO) {

        UUID uuid = UUID.randomUUID(); // 랜덤한 해시값을 만들어줌
        String filename = uuid + "_" + updateDTO.getPic().getOriginalFilename();

        // 프로젝트 실행 파일변경 -> blogv2-1.0.jar
        // 해당 실행파일 경로에 images 폴더가 필요함

        // 이미지 파일을 저장할 경로를 설정
        Path filepath = Paths.get("./images/" + filename);
        try {
            // 이미지 파일을 지정된 경로에 저장
            Files.write(filepath, updateDTO.getPic().getBytes());
            return filename;

        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
    }
}
