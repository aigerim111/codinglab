package com.example.project1.user.repos;

import com.example.project1.user.UserDetails;
import com.example.project1.user.Usr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    UserDetails findUserDetailsByUsr(Usr usr);
}
