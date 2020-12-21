package vn.edu.poly.beecinema.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import vn.edu.poly.beecinema.entity.Taikhoan;
import vn.edu.poly.beecinema.repository.TaikhoanRepository;
import vn.edu.poly.beecinema.service.TaikhoanService;

import java.util.List;
import java.util.Optional;

@Service
public class TaikhoanServiceimpl implements TaikhoanService {

    @Autowired private TaikhoanRepository taikhoanRepository;
    @Override
    public List<Taikhoan> getAllTaikhoan() {
        return (List<Taikhoan>) taikhoanRepository.findAll();
    }

    @Override
    public void saveTaikhoan(Taikhoan taikhoan) {
        taikhoanRepository.save(taikhoan);
    }

    @Override
        public void deleteTaikhoan(String id) {
        taikhoanRepository.deleteById(id);
    }

    @Override
    public Optional<Taikhoan> findTaikhoanById(String id) {
        return taikhoanRepository.findById(id);
    }

    @Override
    public Page<Taikhoan> listAll(int pageNumber, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return taikhoanRepository.findAll(keyword, pageable);
        }
        return  taikhoanRepository.findAll(pageable);
    }

//    @Override
//    public void updateResetPasswordToken(String token, String email) throws TaikhoanNotFoundException {
//        Taikhoan taikhoan = taikhoanRepository.findByEmail(email);
//        if (taikhoan != null) {
//            taikhoan.setResetPasswordToken(token);
//            taikhoanRepository.save(taikhoan);
//        } else {
//            throw new TaikhoanNotFoundException("Could not find with the email" + email);
//        }
//    }
    @Override
    public void updateResetPasswordToken(String token, String email) throws TaikhoanNotFoundException {
        Taikhoan taikhoan = taikhoanRepository.findByEmail(email);
        if (taikhoan != null) {
            taikhoan.setResetPasswordToken(token);
            taikhoanRepository.save(taikhoan);
        } else {
            throw new TaikhoanNotFoundException("Không thể tìm thấy bất kỳ khách hàng nào có email: " + email);
        }
    }

    @Override
    public Taikhoan getByResetPasswordToken(String token) {
        return taikhoanRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(Taikhoan taikhoan, String newPassword) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(newPassword);
//        taikhoan.setMatkhau(encodedPassword);
        taikhoan.setMatkhau(newPassword);
        taikhoan.setResetPasswordToken(null);
        taikhoanRepository.save(taikhoan);
    }

    @Override
    public List<Taikhoan> findByEmail(String email) {
        return taikhoanRepository.findByEmailOrderByUsername(email);

    }

    @Override
    public Taikhoan findTaikhoanByUsernameAndEmail(String username, String email) {
        return taikhoanRepository.findTaikhoanByUsernameAndEmail(username, email);
    }

    @Override
    public Taikhoan findTaikhoanByUsername(String id) {
        return taikhoanRepository.findByUsername(id);
    }

    @Override
    public Page<Taikhoan> listAll(String username, int currentPage, String sortField, String sortDir, String keyword) {
        Pageable pageable = PageRequest.of(currentPage - 1, 3,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );
        if (keyword != null) {
            return taikhoanRepository.findAll(keyword,username, pageable);
        }
        return  taikhoanRepository.findAll1(username,pageable);
    }


}
