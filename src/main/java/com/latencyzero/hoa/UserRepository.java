package com.latencyzero.hoa;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;





public
interface
UserRepository
	extends JpaRepository<User, Long>
{
	Optional<User>				findByLogin(String inLogin);
	Optional<User>				findById(Long inID);
}
