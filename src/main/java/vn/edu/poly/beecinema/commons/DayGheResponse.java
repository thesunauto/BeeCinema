package vn.edu.poly.beecinema.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayGheResponse {
    String id;
    String ten;
    List<GheResponse> gheResponses;
}
