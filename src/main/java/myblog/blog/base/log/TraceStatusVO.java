package myblog.blog.base.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TraceStatusVO {
    private TraceId traceId;
    private Long startTimesMs;
    private String message;
}
