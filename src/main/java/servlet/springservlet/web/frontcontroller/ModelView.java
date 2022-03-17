package servlet.springservlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {

    private String viewName; // View의 논리 이름 (전체 경로로 나타내어지는 실제 위치는 Front Controller에서 덧붙여줌)
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

}
