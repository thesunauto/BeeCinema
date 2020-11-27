package vn.edu.poly.beecinema.service;

import org.springframework.data.domain.Page;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.service.impl.TaikhoanNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TaikhoanService {
    List<Taikhoan> getAllTaikhoan();

    void saveTaikhoan(Taikhoan taikhoan);

    void deleteTaikhoan(String id);

    Optional<Taikhoan> findTaikhoanById(String id);

    Page<Taikhoan> listAll(int pageNumber, String sortField, String sortDir, String keyword);

    void updateResetPasswordToken(String token, String email) throws TaikhoanNotFoundException, TaikhoanNotFoundException;

    Taikhoan getByResetPasswordToken(String token);

    void updatePassword(Taikhoan taikhoan, String newPassword);

    List<Taikhoan>  findByEmail(String email);

    Taikhoan findTaikhoanByUsernameAndEmail(String username, String email);

}
