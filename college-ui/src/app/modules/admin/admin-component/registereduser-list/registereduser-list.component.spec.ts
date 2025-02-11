import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistereduserListComponent } from './registereduser-list.component';

describe('RegistereduserListComponent', () => {
  let component: RegistereduserListComponent;
  let fixture: ComponentFixture<RegistereduserListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegistereduserListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistereduserListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
