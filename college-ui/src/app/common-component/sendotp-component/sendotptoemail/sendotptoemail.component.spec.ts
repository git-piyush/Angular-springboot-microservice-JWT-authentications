import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendotptoemailComponent } from './sendotptoemail.component';

describe('SendotptoemailComponent', () => {
  let component: SendotptoemailComponent;
  let fixture: ComponentFixture<SendotptoemailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SendotptoemailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendotptoemailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
