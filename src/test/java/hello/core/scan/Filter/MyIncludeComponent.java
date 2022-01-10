package hello.core.scan.Filter;

import java.lang.annotation.*;

// ComponentScan에 추가하기 위한 코드들(아래 3줄)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
