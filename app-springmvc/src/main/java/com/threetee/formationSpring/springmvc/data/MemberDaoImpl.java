/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.threetee.formationSpring.springmvc.data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.threetee.formationSpring.springmvc.model.Member;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session session() {
    	return sessionFactory.getCurrentSession();
	}

    public Member findById(Long id) {
        return (Member) session().get(Member.class, id);
    }

    public Member findByEmail(String email) {
    	Criteria criteria = session().createCriteria(Member.class);
    	return  (Member) criteria.add(Restrictions.eqProperty("email", email)).uniqueResult();

      
    }

    public List<Member> findAllOrderedByName() {
    	return session().createCriteria(Member.class).addOrder(Order.asc("name")).list();
    }

    public void register(Member member) {
    	session().save(member);
        return;
    }
}
