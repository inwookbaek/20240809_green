package com.lec.spmvc.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("normal")
@Primary
//@Qualifier("normal")
public class SampleDAOImpl implements SampleDAO {

}
