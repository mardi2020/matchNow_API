package com.example.matchnow.project;

import com.example.matchnow.user.Writer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UploadProjectDTO {

    @NotBlank(message="제목이 필요합니다.") // null, "", " " 허용하지 않음
    @Size(min = 1, max = 15, message="제목의 길이는 1부터 10까지 허용합니다.")
    private String title;

    @NotNull(message = "본문이 비어있습니다.")
    @Size(min = 1)
    private String mainText;

    private Writer user;

    private String image;

    private int wantCnt;

    private int nowPeopleCnt;

    private List<Writer> userList;
}
