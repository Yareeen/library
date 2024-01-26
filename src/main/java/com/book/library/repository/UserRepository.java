package com.book.library.repository;

import com.book.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //Bu, arama işlemi sonucunda bir kullanıcı bulunabilirse, bu kullanıcıyı içeren Optional<User> nesnesi dönecektir. Eğer kullanıcı bulunamazsa, Optional içinde null olmayan bir değer olmadığı için Optional.empty() dönecektir.
}